package com.github.rozyhead.balmn.common.domain.model

import java.util.concurrent.CompletableFuture

interface DomainRepository<in ID, EVENT : DomainEvent, out ENTITY : DomainEntity<EVENT, ENTITY>> {

  fun find(id: ID): Pair<ENTITY, Version>?

  fun save(id: ID, version: Version, vararg additionalEvents: EVENT): CompletableFuture<Unit>

}