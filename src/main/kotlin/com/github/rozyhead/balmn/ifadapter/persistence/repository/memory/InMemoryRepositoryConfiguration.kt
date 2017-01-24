package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import org.springframework.context.annotation.Bean

class InMemoryRepositoryConfiguration {

  @Bean
  fun inMemoryBoardRepository() = InMemoryBoardRepository()

  @Bean
  fun inMemoryCardRepository() = InMemoryCardRepository()

  @Bean
  fun inMemoryCommentRepository() = InMemoryCommentRepository()

  @Bean
  fun inMemoryPasswordAuthenticationRepository() = InMemoryPasswordAuthenticationRepository()

  @Bean
  fun inMemorySheetRepository() = InMemorySheetRepository()

  @Bean
  fun inMemoryUserAccountRepository() = InMemoryUserAccountRepository()

}

