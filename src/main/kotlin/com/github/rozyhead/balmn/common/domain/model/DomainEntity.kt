package com.github.rozyhead.balmn.common.domain.model

interface DomainEntity<E : DomainEvent, out SELF : DomainEntity<E, SELF>> {

  infix fun apply(event: E): SELF

  infix fun and(event: E): Pair<SELF, E> = Pair(this apply event, event)

}