package com.github.rozyhead.balmn.infrastructure.eventstore.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventMessage
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.infrastructure.eventstore.jdbc.tables.EventMessages
import com.github.rozyhead.balmn.infrastructure.eventstore.jdbc.tables.StreamVersions
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import java.time.LocalDateTime

fun jodaTimeToJavaTime(dateTime: DateTime): LocalDateTime {
  return LocalDateTime.from(dateTime.toDate().toInstant())
}

class JdbcEventStore(
    val objectMapper: ObjectMapper
) : EventStore {

  override fun exists(streamId: String): Boolean {
    return StreamVersions.select { StreamVersions.streamId eq streamId }.count() > 0
  }

  override fun eventMessages(streamId: String): List<EventMessage>? {
    return EventMessages.select { EventMessages.streamId eq streamId }.orderBy(EventMessages.eventId).map {
      val eventId = it[EventMessages.eventId]
      val streamVersion = it[EventMessages.streamVersion]
      val eventType = it[EventMessages.eventType]
      val payloadText = it[EventMessages.payload]
      val createdAt = it[EventMessages.createdAt]

      val eventClass = Class.forName(eventType)
      val payload = objectMapper.readValue(payloadText, eventClass)

      EventMessage(eventId, streamId, streamVersion, payload, jodaTimeToJavaTime(createdAt))
    }
  }

  override fun batchAppend(streamId: String, version: Long, events: List<Any>) {
    val currentVersion = StreamVersions.select { StreamVersions.streamId eq streamId }.forUpdate()
        .map { it[StreamVersions.streamVersion] }.single()

    require(version == currentVersion, { "Version conflict" })

    EventMessages.batchInsert(events) {
      this[EventMessages.streamId] = streamId
      this[EventMessages.streamVersion] = currentVersion + 1
      this[EventMessages.eventType] = it.javaClass.name
      this[EventMessages.payload] = objectMapper.writeValueAsString(it)
      this[EventMessages.createdAt] = DateTime()
    }

    StreamVersions.update({ StreamVersions.streamId eq streamId }) {
      it[StreamVersions.streamVersion] = currentVersion + events.size
    }
  }

}