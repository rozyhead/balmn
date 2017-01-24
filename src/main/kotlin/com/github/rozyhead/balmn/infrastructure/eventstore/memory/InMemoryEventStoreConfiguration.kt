package com.github.rozyhead.balmn.infrastructure.eventstore.memory

import org.springframework.context.annotation.Bean
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

class InMemoryEventStoreConfiguration {

  @Bean
  fun inMemoryEventStore() = InMemoryEventStore()

  @Bean
  fun inMemoryReadProjection() = InMemoryReadProjection(inMemoryEventStore())

}

