package com.github.rozyhead.balmn.infrastructure.eventstore.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

object Versions : Table() {
  val streamId = varchar("stream_id", 255)
  val version = long("version")
}

object Events : Table() {
  val id = long("id").primaryKey()
  val streamId = varchar("stream_id", 255) references Versions.streamId
  val eventType = varchar("event_type", 255)
  val payload = text("payload").nullable()
}

class JdbcEventStore(
    val objectMapper: ObjectMapper
) : EventStore {

  override fun exists(streamId: String): Boolean {
    return Versions.select { Versions.streamId eq streamId }.count() > 0
  }

  override fun events(streamId: String): List<DomainEvent>? {
    return Events.select { Events.streamId eq streamId }.orderBy(Events.id).map {
      val eventType = it[Events.eventType]
      val payload = it[Events.payload]

      val eventClass = Class.forName(eventType)
      objectMapper.readValue(payload, eventClass) as DomainEvent
    }
  }

  override fun batchAppend(streamId: String, version: Long, events: List<DomainEvent>) {
    val currentVersion = Versions.select { Versions.streamId eq streamId }.forUpdate()
        .map { it[Versions.version] }.single()

    require(version == currentVersion, { "Version conflict" })

    Events.batchInsert(events) {
      this[Events.streamId] = streamId
      this[Events.eventType] = it.javaClass.name
      this[Events.payload] = objectMapper.writeValueAsString(it)
    }

    Versions.update({ Versions.streamId eq streamId }) {
      it[Versions.version] = currentVersion + events.size
    }
  }

}