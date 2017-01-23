package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent
import com.github.rozyhead.balmn.service.repository.UserAccountRepository
import com.github.rozyhead.balmn.util.ddd.Version

class InMemoryUserAccountRepository : UserAccountRepository {

  val helper = InMemoryRepositoryHelper<UserAccountEvent, UserAccount, AccountName>(
      emptyEntity = UserAccount()
  )

  override fun exists(accountName: AccountName): Boolean = helper.existsInMemory(accountName)

  override fun findByAccountName(accountName: AccountName): Pair<UserAccount, Version>?
      = helper.findByMemory(accountName)

  override fun save(accountName: AccountName, version: Version, vararg additionalEvents: UserAccountEvent)
      = helper.saveToMemory(accountName, version, *additionalEvents)

}