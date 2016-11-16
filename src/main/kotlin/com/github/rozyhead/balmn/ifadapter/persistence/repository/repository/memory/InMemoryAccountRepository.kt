package com.github.rozyhead.balmn.ifadapter.persistence.repository.repository.memory

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.AccountRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryAccountRepository(
    val inMemoryUserAccountRepository: InMemoryUserAccountRepository
) : AccountRepository {

  override fun exists(accountName: AccountName): Boolean {
    return inMemoryUserAccountRepository.findByAccountName(accountName) != null;
  }

  override fun findByAccountName(accountName: AccountName): Account? {
    return inMemoryUserAccountRepository.findByAccountName(accountName)?.first
  }

}