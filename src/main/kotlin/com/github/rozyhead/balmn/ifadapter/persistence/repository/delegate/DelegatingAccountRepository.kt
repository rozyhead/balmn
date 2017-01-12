package com.github.rozyhead.balmn.ifadapter.persistence.repository.delegate

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.service.repository.AccountRepository
import com.github.rozyhead.balmn.service.repository.UserAccountRepository
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