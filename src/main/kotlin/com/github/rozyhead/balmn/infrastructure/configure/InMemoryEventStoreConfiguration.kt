package com.github.rozyhead.balmn.infrastructure.configure

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.rozyhead.balmn.infrastructure.eventstore.memory.InMemoryEventStore
import org.springframework.context.annotation.Bean

class InMemoryEventStoreConfiguration {

  @Bean
  fun inMemoryEventStore(objectMapper: ObjectMapper) = InMemoryEventStore(objectMapper)

}

