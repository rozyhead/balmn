package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent
import com.github.rozyhead.balmn.service.repository.UserAccountRepository
import com.github.rozyhead.balmn.util.ddd.Version

class InMemoryUserAccountRepository : UserAccountRepository, AbstractInMemoryRepository<UserAccountEvent, UserAccount, AccountName>() {

  override val emptyEntity: UserAccount
    get() = UserAccount()

  override fun findByAccountName(accountName: AccountName): Pair<UserAccount, Version>? = findByMemory(accountName)

  override fun save(accountName: AccountName, version: Version, vararg additionalEvents: UserAccountEvent) = saveToMemory(accountName, version, *additionalEvents)

}