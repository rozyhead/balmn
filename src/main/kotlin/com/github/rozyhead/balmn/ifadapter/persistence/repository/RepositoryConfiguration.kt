package com.github.rozyhead.balmn.ifadapter.persistence.repository

import com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore.EventStoreRepositoryConfiguration
import com.github.rozyhead.balmn.ifadapter.persistence.repository.memory.InMemoryRepositoryConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

const val STRATEGY_KEY = "balmn.repository.strategy"

@Configuration
class RepositoryConfiguration {

  @Configuration
  @ConditionalOnProperty(name = arrayOf(STRATEGY_KEY), havingValue = "memory", matchIfMissing = true)
  @Import(InMemoryRepositoryConfiguration::class)
  class InMemory

  @Configuration
  @ConditionalOnProperty(name = arrayOf(STRATEGY_KEY), havingValue = "eventstore")
  @Import(EventStoreRepositoryConfiguration::class)
  class EventStore

}