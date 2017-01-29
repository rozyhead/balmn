package com.github.rozyhead.balmn.infrastructure.eventstore.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

class JdbcEventStoreConfiguration {

  @Bean
  fun jdbcEventStore(objectMapper: ObjectMapper) = JdbcEventStore(objectMapper)

  @Bean
  fun jdbcReadProjection(objectMapper: ObjectMapper)
      = JdbcReadProjection(objectMapper, jdbcReadProjectionExecutorService().scheduledExecutor)

  @Bean
  fun jdbcReadProjectionExecutorService(): ThreadPoolTaskScheduler {
    val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
    with(ThreadPoolTaskScheduler()) {
      poolSize = 10
      threadNamePrefix = "jdbc-read-projection-"
      isDaemon = false
    }
    return threadPoolTaskScheduler
  }

}

