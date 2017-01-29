package com.github.rozyhead.balmn.infrastructure.eventstore.jdbc.tables

import org.jetbrains.exposed.sql.Table

object EventMessages : Table("event_messages") {
  val eventId = long("eventId").primaryKey()
  val streamId = varchar("stream_id", 255) references StreamVersions.streamId
  val streamVersion = long("stream_version")
  val eventType = varchar("event_type", 255)
  val payload = text("payload")
  val createdAt = datetime("created_at")
}