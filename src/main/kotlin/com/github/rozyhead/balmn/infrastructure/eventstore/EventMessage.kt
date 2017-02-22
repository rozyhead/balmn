package com.github.rozyhead.balmn.infrastructure.eventstore

import java.time.OffsetDateTime

data class EventMessage(
    val eventId: Long,
    val streamId: String,
    val version: Long,
    val eventType: String,
    val payload: String,
    val createdAt: OffsetDateTime
)
