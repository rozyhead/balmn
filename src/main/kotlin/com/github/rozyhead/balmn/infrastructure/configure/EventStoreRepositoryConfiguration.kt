package com.github.rozyhead.balmn.infrastructure.configure

import com.github.rozyhead.balmn.account.port.adapter.repository.eventstore.EventStoreUserAccountRepository
import com.github.rozyhead.balmn.authentication.port.adapter.repository.eventstore.EventStorePasswordAuthenticationRepository
import com.github.rozyhead.balmn.board.port.adapter.repository.eventstore.EventStoreBoardRepository
import com.github.rozyhead.balmn.board.port.adapter.repository.eventstore.EventStoreCardRepository
import com.github.rozyhead.balmn.board.port.adapter.repository.eventstore.EventStoreCommentRepository
import com.github.rozyhead.balmn.board.port.adapter.repository.eventstore.EventStoreSheetRepository
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import org.springframework.context.annotation.Bean

class EventStoreRepositoryConfiguration {

  @Bean
  fun eventStoreBoardRepository(eventStore: EventStore) = EventStoreBoardRepository(eventStore)

  @Bean
  fun eventStoreCardRepository(eventStore: EventStore) = EventStoreCardRepository(eventStore)

  @Bean
  fun eventStoreCommentRepository(eventStore: EventStore) = EventStoreCommentRepository(eventStore)

  @Bean
  fun eventStorePasswordAuthenticationRepository(eventStore: EventStore) = EventStorePasswordAuthenticationRepository(eventStore)

  @Bean
  fun eventStoreSheetRepository(eventStore: EventStore) = EventStoreSheetRepository(eventStore)

  @Bean
  fun eventStoreUserAccountRepository(eventStore: EventStore) = EventStoreUserAccountRepository(eventStore)

}

