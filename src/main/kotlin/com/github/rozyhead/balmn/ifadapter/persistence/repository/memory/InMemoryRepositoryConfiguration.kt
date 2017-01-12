package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.ifadapter.persistence.repository.delegate.DelegatingAccountRepository
import com.github.rozyhead.balmn.service.repository.*
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InMemoryRepositoryConfiguration {

  @Bean
  @ConditionalOnMissingBean(BoardRepository::class)
  fun inMemoryBoardRepository() = InMemoryBoardRepository()

  @Bean
  @ConditionalOnMissingBean(CardRepository::class)
  fun inMemoryCardRepository() = InMemoryCardRepository()

  @Bean
  @ConditionalOnMissingBean(CommentRepository::class)
  fun inMemoryCommentRepository() = InMemoryCommentRepository()

  @Bean
  @ConditionalOnMissingBean(PasswordAuthenticationRepository::class)
  fun inMemoryPasswordAuthenticationRepository() = InMemoryPasswordAuthenticationRepository()

  @Bean
  @ConditionalOnMissingBean(SheetRepository::class)
  fun inMemorySheetRepository() = InMemorySheetRepository()

  @Bean
  @ConditionalOnMissingBean(UserAccountRepository::class)
  fun inMemoryUserAccountRepository() = InMemoryUserAccountRepository()

}

