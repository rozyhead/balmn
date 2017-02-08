package com.github.rozyhead.balmn.common.domain.model

import java.time.LocalDateTime

interface DomainEvent {
  val occurredOn: LocalDateTime
}
