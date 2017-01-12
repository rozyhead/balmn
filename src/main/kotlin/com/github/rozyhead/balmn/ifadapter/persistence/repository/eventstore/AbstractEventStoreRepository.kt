package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.util.ddd.DomainEntity
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import kotlin.reflect.KClass

abstract class AbstractEventStoreRepository<EVENT : DomainEvent, out ENTITY : DomainEntity<EVENT, ENTITY>, ID>(
    val eventStore: EventStore
) {

  abstract val eventClass: KClass<EVENT>
  abstract val emptyEntity: ENTITY

  abstract fun streamIdOf(entityId: ID): String

  fun existsInStore(entityId: ID): Boolean = eventStore.exists(streamIdOf(entityId))

  fun findByStore(entityId: ID): Pair<ENTITY, List<EVENT>>? {
    val events = eventStore.events(streamIdOf(entityId))?.map { eventClass.java.cast(it) } ?: return null
    val entity = events.fold(emptyEntity, { entity, event -> entity apply event })
    return Pair(entity, events)
  }

  fun saveToStore(entityId: ID, events: List<EVENT>, oldEvents: List<EVENT>) {
    eventStore.batchAppend(streamIdOf(entityId), oldEvents.size.toLong(), events)
  }

}