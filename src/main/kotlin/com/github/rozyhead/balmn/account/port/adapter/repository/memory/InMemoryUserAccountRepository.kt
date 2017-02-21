package com.github.rozyhead.balmn.account.port.adapter.repository.memory

import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper

class InMemoryUserAccountRepository : UserAccountRepository {

  val helper = InMemoryRepositoryHelper<UserAccountEvent, UserAccount, AccountId>(
      emptyEntity = UserAccount()
  )

  override fun exists(id: AccountId): Boolean = helper.existsInMemory(id)

  override fun find(id: AccountId): Pair<UserAccount, Version>?
      = helper.findByMemory(id)

  override fun save(id: AccountId, version: Version, vararg additionalEvents: UserAccountEvent)
      = helper.saveToMemory(id, version, *additionalEvents)

}