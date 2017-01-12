package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.util.ddd.DomainEntity
import com.github.rozyhead.balmn.util.ddd.DomainEvent

abstract class AbstractInMemoryRepository<EVENT : DomainEvent, out ENTITY : DomainEntity<EVENT, ENTITY>, ID> {

  abstract val emptyEntity: ENTITY

  val events = mutableMapOf<ID, List<EVENT>>()

  fun existsInMemory(entityId: ID): Boolean = events.contains(entityId)

  fun findByMemory(entityId: ID): Pair<ENTITY, List<EVENT>>? {
    val events = this.events[entityId] ?: return null
    return Pair(events.fold(emptyEntity, { a, b -> a apply b }), events)
  }

  fun saveToMemory(entityId: ID, events: List<EVENT>, oldEvents: List<EVENT>) {
    val savedOldEvents = this.events.getOrElse(entityId, { emptyList() })
    require(savedOldEvents.size == oldEvents.size)

    this.events[entityId] = savedOldEvents + events
  }

}