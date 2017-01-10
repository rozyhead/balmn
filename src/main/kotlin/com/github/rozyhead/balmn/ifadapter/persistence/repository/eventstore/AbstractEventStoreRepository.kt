package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.util.ddd.DomainEntity
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import kotlin.reflect.KClass

abstract class AbstractEventStoreRepository<EV : DomainEvent, out EN : DomainEntity<EV, EN>>(
    val eventStore: EventStore
) {

  abstract val eventClass: KClass<EV>
  abstract val emptyEntity: EN

  fun exists(streamId: String): Boolean = eventStore.exists(streamId)

  fun findByStore(streamId: String): Pair<EN, List<EV>>? {
    val events = eventStore.events(streamId)?.map { eventClass.java.cast(it) } ?: return null
    val entity = events.fold(emptyEntity, { entity, event -> entity apply event })
    return Pair(entity, events)
  }

  fun saveToStore(streamId: String, events: List<EV>, oldEvents: List<EV>) {
    eventStore.batchAppend(streamId, oldEvents.size.toLong(), events)
  }

}