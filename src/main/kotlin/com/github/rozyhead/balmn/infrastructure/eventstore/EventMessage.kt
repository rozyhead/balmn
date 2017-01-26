package com.github.rozyhead.balmn.infrastructure.eventstore

import java.time.LocalDateTime

data class EventMessage(
    val eventId: Long,
    val streamId: String,
    val version: Long,
    val payload: Any,
    val createdAt: LocalDateTime
)
