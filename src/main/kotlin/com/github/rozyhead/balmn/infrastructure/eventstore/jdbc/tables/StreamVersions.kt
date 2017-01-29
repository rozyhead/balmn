package com.github.rozyhead.balmn.infrastructure.eventstore.jdbc.tables

import org.jetbrains.exposed.sql.Table

object StreamVersions : Table("stream_versions") {
  val streamId = varchar("stream_id", 255)
  val streamVersion = long("stream_version")
}