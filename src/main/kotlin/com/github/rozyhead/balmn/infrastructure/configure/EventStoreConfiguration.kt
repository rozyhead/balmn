package com.github.rozyhead.balmn.infrastructure.configure

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

const val EVENTSTORE_STRATEGY_KEY = "balmn.eventstore.strategy"

@Configuration
@ConditionalOnBean(EventStoreRepositoryConfiguration::class)
class EventStoreConfiguration {

  @Configuration
  @ConditionalOnProperty(name = arrayOf(EVENTSTORE_STRATEGY_KEY), havingValue = "memory", matchIfMissing = true)
  @Import(InMemoryEventStoreConfiguration::class)
  class InMemory

  @Configuration
  @ConditionalOnProperty(name = arrayOf(EVENTSTORE_STRATEGY_KEY), havingValue = "jdbc")
  @Import(JdbcEventStoreConfiguration::class)
  class Jdbc

}