package com.github.rozyhead.balmn.configure.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.msemys.esjc.EventStore
import com.github.rozyhead.balmn.account.port.adapter.repository.eventstore.EventStoreUserAccountRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore.EventStoreBoardRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore.EventStoreCardRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore.EventStoreCommentRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore.EventStoreSheetRepository
import org.springframework.context.annotation.Bean

class EventStoreRepositoryConfiguration {

  @Bean
  fun eventStoreBoardRepository(eventStore: EventStore, objectMapper: ObjectMapper)
      = EventStoreBoardRepository(eventStore, objectMapper)

  @Bean
  fun eventStoreCardRepository(eventStore: EventStore, objectMapper: ObjectMapper)
      = EventStoreCardRepository(eventStore, objectMapper)

  @Bean
  fun eventStoreCommentRepository(eventStore: EventStore, objectMapper: ObjectMapper)
      = EventStoreCommentRepository(eventStore, objectMapper)

  @Bean
  fun eventStoreSheetRepository(eventStore: EventStore, objectMapper: ObjectMapper)
      = EventStoreSheetRepository(eventStore, objectMapper)

  @Bean
  fun eventStoreUserAccountRepository(eventStore: EventStore, objectMapper: ObjectMapper)
      = EventStoreUserAccountRepository(eventStore, objectMapper)

}

