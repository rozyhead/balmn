package com.github.rozyhead.balmn.infrastructure.eventstore

import com.github.rozyhead.balmn.util.ddd.DomainEvent

interface EventStore {

  fun exists(streamId: String): Boolean

  fun events(streamId: String): List<DomainEvent>?

  fun batchAppend(streamId: String, version: Long, events: Iterable<DomainEvent>)

}