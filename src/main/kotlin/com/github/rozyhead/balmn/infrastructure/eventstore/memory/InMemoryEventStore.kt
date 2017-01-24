package com.github.rozyhead.balmn.infrastructure.eventstore.memory

import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.util.ddd.DomainEvent

class InMemoryEventStore : EventStore {

  val events = mutableListOf<Pair<String, DomainEvent>>()
  val subscribers = mutableListOf<(String, DomainEvent) -> Unit>()

  fun subscribe(subscriber: (String, DomainEvent) -> Unit): () -> Unit {
    this.subscribers += subscriber
    return { this.subscribers -= subscriber }
  }

  fun publish(streamId: String, event: DomainEvent) {
    subscribers.forEach { it(streamId, event) }
  }

  override fun exists(streamId: String): Boolean {
    return this.events.find { it.first == streamId } != null
  }

  override fun events(streamId: String): List<DomainEvent>? {
    val events = this.events.filter { it.first == streamId }.map { it.second }
    return if (events.isNotEmpty()) events else null
  }

  override fun batchAppend(streamId: String, version: Long, events: List<DomainEvent>) {
    val oldEvents = this.events(streamId)
    require(oldEvents == null || oldEvents.size.toLong() == version, { "Stream version conflicts" })

    this.events.addAll(events.map { Pair(streamId, it) })
    events.forEach { this.publish(streamId, it) }
  }

}