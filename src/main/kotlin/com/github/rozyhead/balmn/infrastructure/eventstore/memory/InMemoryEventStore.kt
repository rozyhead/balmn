package com.github.rozyhead.balmn.infrastructure.eventstore.memory

import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import org.springframework.stereotype.Component

@Component
class InMemoryEventStore : EventStore {

  val events = mutableMapOf<String, List<DomainEvent>>()

  override fun exists(streamId: String): Boolean {
    return events.contains(streamId)
  }

  override fun events(streamId: String): List<DomainEvent>? {
    return this.events[streamId]
  }

  override fun batchAppend(streamId: String, version: Long, events: Iterable<DomainEvent>) {
    val oldEvents = this.events.getOrElse(streamId) { emptyList() }
    require(oldEvents.size.toLong() == version, { "Stream version conflicts" })

    this.events[streamId] = oldEvents + events
  }

}