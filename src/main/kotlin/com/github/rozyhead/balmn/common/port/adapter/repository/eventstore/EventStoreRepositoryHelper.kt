package com.github.rozyhead.balmn.common.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import kotlin.reflect.KClass

class EventStoreRepositoryHelper<EVENT : DomainEvent, out ENTITY : DomainEntity<EVENT, ENTITY>, in ID>(
    val eventStore: EventStore,
    val eventClass: KClass<EVENT>,
    val emptyEntity: ENTITY,
    val streamIdOf: (ID) -> String
) {

  fun existsInStore(entityId: ID): Boolean = eventStore.exists(streamIdOf(entityId))

  fun findByStore(entityId: ID): Pair<ENTITY, Version>? {
    val events = eventStore.eventMessages(streamIdOf(entityId))?.map { eventClass.java.cast(it.payload) } ?: return null
    val entity = events.fold(emptyEntity, { entity, event -> entity apply event })
    return Pair(entity, Version(events.size.toLong()))
  }

  fun saveToStore(entityId: ID, version: Version, vararg additionalEvents: EVENT) {
    eventStore.batchAppend(streamIdOf(entityId), version.value, additionalEvents.toList())
  }

}