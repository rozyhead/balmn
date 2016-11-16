package com.github.rozyhead.balmn.util.ddd

import java.time.LocalDateTime

interface DomainEvent {
  val occurredOn: LocalDateTime
}
