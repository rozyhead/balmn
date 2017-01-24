package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

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

