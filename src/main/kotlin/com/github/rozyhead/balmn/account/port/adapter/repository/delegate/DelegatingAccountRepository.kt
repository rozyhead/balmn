package com.github.rozyhead.balmn.account.port.adapter.repository.delegate

import com.github.rozyhead.balmn.account.application.repository.AccountRepository
import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.Account
import com.github.rozyhead.balmn.account.domain.model.AccountName
import org.springframework.stereotype.Repository

@Repository
class DelegatingAccountRepository(
    val userAccountRepository: UserAccountRepository
) : AccountRepository {

  override fun exists(accountName: AccountName): Boolean {
    return userAccountRepository.findByAccountName(accountName) != null
  }

  override fun findByAccountName(accountName: AccountName): Account? {
    return userAccountRepository.findByAccountName(accountName)?.first
  }

}