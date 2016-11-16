package com.github.rozyhead.balmn.util.ddd

interface DomainEntity<in E : DomainEvent, out SELF : DomainEntity<E, SELF>> {

  infix fun <E> apply(event: E): SELF

  infix fun <E> and(event: E): Pair<SELF, E> = Pair(this apply event, event)

}