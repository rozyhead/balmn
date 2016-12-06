package com.github.rozyhead.balmn.util.ddd

interface DomainEntity<E : DomainEvent, out SELF : DomainEntity<E, SELF>> {

  infix fun apply(event: E): SELF

  infix fun and(event: E): Pair<SELF, E> = Pair(this apply event, event)

}