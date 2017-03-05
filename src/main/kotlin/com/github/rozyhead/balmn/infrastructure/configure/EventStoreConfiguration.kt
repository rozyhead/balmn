package com.github.rozyhead.balmn.infrastructure.configure

import com.github.msemys.esjc.EventStore
import com.github.msemys.esjc.EventStoreBuilder
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean(EventStoreRepositoryConfiguration::class)
class EventStoreConfiguration {

  @Bean
  fun eventStore(): EventStore {
    return EventStoreBuilder.newBuilder()
        .singleNodeAddress("localhost", 1113)
        .build()
  }

}