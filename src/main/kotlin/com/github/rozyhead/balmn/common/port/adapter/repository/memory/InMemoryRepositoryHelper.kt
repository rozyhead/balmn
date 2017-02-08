package com.github.rozyhead.balmn.common.port.adapter.repository.memory

import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import com.github.rozyhead.balmn.common.domain.model.Version

class InMemoryRepositoryHelper<EVENT : DomainEvent, out ENTITY : DomainEntity<EVENT, ENTITY>, ID>(
    val emptyEntity: ENTITY
) {

  val events = mutableMapOf<ID, List<EVENT>>()

  fun existsInMemory(entityId: ID): Boolean = events.contains(entityId)

  fun findByMemory(entityId: ID): Pair<ENTITY, Version>? {
    val events = this.events[entityId] ?: return null
    return Pair(events.fold(emptyEntity, { a, b -> a apply b }), Version(events.size.toLong()))
  }

  fun saveToMemory(entityId: ID, version: Version, vararg additionalEvents: EVENT) {
    val savedOldEvents = this.events.getOrElse(entityId, { emptyList() })
    require(version.value == savedOldEvents.size.toLong())

    this.events[entityId] = savedOldEvents + additionalEvents
  }

}