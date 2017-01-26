package com.github.rozyhead.balmn.infrastructure.eventstore

interface EventStore {

  fun exists(streamId: String): Boolean

  fun eventMessages(streamId: String): List<EventMessage>?

  fun batchAppend(streamId: String, version: Long, events: List<Any>)

}