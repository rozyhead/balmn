package com.github.rozyhead.balmn.infrastructure.configure

import com.github.rozyhead.balmn.account.port.adapter.repository.memory.InMemoryUserAccountRepository
import com.github.rozyhead.balmn.authentication.port.adapter.repository.memory.InMemoryUserRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.memory.InMemoryBoardRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.memory.InMemoryCardRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.memory.InMemoryCommentRepository
import com.github.rozyhead.balmn.kanban.port.adapter.repository.memory.InMemorySheetRepository
import org.springframework.context.annotation.Bean

class InMemoryRepositoryConfiguration {

  @Bean
  fun inMemoryBoardRepository() = InMemoryBoardRepository()

  @Bean
  fun inMemoryCardRepository() = InMemoryCardRepository()

  @Bean
  fun inMemoryCommentRepository() = InMemoryCommentRepository()

  @Bean
  fun inMemoryPasswordAuthenticationRepository() = InMemoryUserRepository()

  @Bean
  fun inMemorySheetRepository() = InMemorySheetRepository()

  @Bean
  fun inMemoryUserAccountRepository() = InMemoryUserAccountRepository()

}

