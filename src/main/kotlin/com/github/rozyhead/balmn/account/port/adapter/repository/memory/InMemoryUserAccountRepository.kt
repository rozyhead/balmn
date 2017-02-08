package com.github.rozyhead.balmn.account.port.adapter.repository.memory

import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper

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