package com.github.rozyhead.balmn.infrastructure.eventstore

import com.github.rozyhead.balmn.util.ddd.DomainEvent

interface EventSerializer {

  fun serialize(event: DomainEvent): Array<Byte>

  fun deserialize(type: String, bytes: Array<Byte>): DomainEvent

}