package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.service.repository.AccountRepository
import com.github.rozyhead.balmn.service.repository.UserAccountRepository

class InMemoryAccountRepository(
    val userAccountRepository: UserAccountRepository
) : AccountRepository {

  override fun exists(accountName: AccountName): Boolean {
    return userAccountRepository.findByAccountName(accountName) != null
  }

  override fun findByAccountName(accountName: AccountName): Account? {
    return userAccountRepository.findByAccountName(accountName)?.first
  }

}