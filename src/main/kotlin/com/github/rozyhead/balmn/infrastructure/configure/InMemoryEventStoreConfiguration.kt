package com.github.rozyhead.balmn.infrastructure.configure

import com.github.rozyhead.balmn.infrastructure.eventstore.memory.InMemoryEventStore
import org.springframework.context.annotation.Bean

class InMemoryEventStoreConfiguration {

  @Bean
  fun inMemoryEventStore() = InMemoryEventStore()

}

