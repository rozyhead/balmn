package com.github.rozyhead.balmn.infrastructure.eventstore.memory

import org.springframework.context.annotation.Bean
import java.time.Clock

class InMemoryEventStoreConfiguration {

  @Bean
  fun inMemoryEventStore() = InMemoryEventStore()

}

