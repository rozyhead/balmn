package com.github.rozyhead.balmn.infrastructure.eventstore

import com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore.EventStoreRepositoryConfiguration
import com.github.rozyhead.balmn.infrastructure.eventstore.jdbc.JdbcEventStoreConfiguration
import com.github.rozyhead.balmn.infrastructure.eventstore.memory.InMemoryEventStoreConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

const val STRATEGY_KEY = "balmn.eventstore.strategy"

@Configuration
@ConditionalOnBean(EventStoreRepositoryConfiguration::class)
class EventStoreConfiguration {

  @Configuration
  @ConditionalOnProperty(name = arrayOf(STRATEGY_KEY), havingValue = "memory", matchIfMissing = true)
  @Import(InMemoryEventStoreConfiguration::class)
  class InMemory

  @Configuration
  @ConditionalOnProperty(name = arrayOf(STRATEGY_KEY), havingValue = "jdbc")
  @Import(JdbcEventStoreConfiguration::class)
  class Jdbc

}