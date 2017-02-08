package com.github.rozyhead.balmn.infrastructure.configure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

const val REPOSITORY_STRATEGY_KEY = "balmn.repository.strategy"

@Configuration
class RepositoryConfiguration {

  @Configuration
  @ConditionalOnProperty(name = arrayOf(REPOSITORY_STRATEGY_KEY), havingValue = "memory", matchIfMissing = true)
  @Import(InMemoryRepositoryConfiguration::class)
  class InMemory

  @Configuration
  @ConditionalOnProperty(name = arrayOf(REPOSITORY_STRATEGY_KEY), havingValue = "eventstore")
  @Import(EventStoreRepositoryConfiguration::class)
  class EventStore

}