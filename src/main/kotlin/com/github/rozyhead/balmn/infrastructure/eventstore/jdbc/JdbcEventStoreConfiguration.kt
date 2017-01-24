package com.github.rozyhead.balmn.infrastructure.eventstore.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean

class JdbcEventStoreConfiguration {

  @Bean
  fun jdbcEventStore(objectMapper: ObjectMapper) = JdbcEventStore(objectMapper)

}

