package com.github.rozyhead.balmn.ifadapter.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.account.UserAccountRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryUserAccountRepository : UserAccountRepository {

  val entities = mutableMapOf<AccountName, UserAccount>()

  override fun findByAccountName(accountName: AccountName): UserAccount? {
    return entities[accountName]
  }

  override fun save(userAccount: UserAccount) {
    entities[userAccount.accountName] = userAccount
  }

}