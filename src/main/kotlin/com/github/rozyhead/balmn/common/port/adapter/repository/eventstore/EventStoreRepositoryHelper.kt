package com.github.rozyhead.balmn.common.port.adapter.repository.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.msemys.esjc.EventData
import com.github.msemys.esjc.EventStore
import com.github.msemys.esjc.ExpectedVersion
import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import java.util.concurrent.CompletableFuture

class EventStoreRepositoryHelper<EVENT : DomainEvent, out ENTITY : DomainEntity<EVENT, ENTITY>, in ID>(
    val eventStore: EventStore,
    val objectMapper: ObjectMapper,
    val emptyEntity: ENTITY,
    val streamIdOf: (ID) -> String
) {

  fun findByStore(entityId: ID): Pair<ENTITY, Version>? {
    var eventNumber = -1
    val entity = eventStore.iterateStreamEventsForward(streamIdOf(entityId), 0, 1024, false)
        .asSequence()
        .map {
          val type = Class.forName(it.event.eventType)
          eventNumber = Math.max(eventNumber, it.event.eventNumber)
          objectMapper.readValue(it.event.data, type) as EVENT
        }
        .fold(emptyEntity, { entity, event -> entity apply event })

    return Pair(entity, Version(eventNumber.toLong()))
  }

  fun saveToStore(entityId: ID, version: Version, vararg additionalEvents: EVENT): CompletableFuture<Unit> {
    return eventStore.appendToStream(streamIdOf(entityId), ExpectedVersion.of(version.value.toInt()), additionalEvents.map {
      EventData.newBuilder()
          .type(it.javaClass.name)
          .jsonData(objectMapper.writeValueAsBytes(it))
          .build()
    }).thenApply { Unit }
  }

}