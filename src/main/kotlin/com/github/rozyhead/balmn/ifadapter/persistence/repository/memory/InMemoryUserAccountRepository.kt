package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent
import com.github.rozyhead.balmn.service.repository.UserAccountRepository

class InMemoryUserAccountRepository : UserAccountRepository, AbstractInMemoryRepository<UserAccountEvent, UserAccount, AccountName>() {

  override val emptyEntity: UserAccount
    get() = UserAccount()

  override fun findByAccountName(accountName: AccountName): Pair<UserAccount, List<UserAccountEvent>>? = findByMemory(accountName)

  override fun save(accountName: AccountName, events: List<UserAccountEvent>, oldEvents: List<UserAccountEvent>) = saveToMemory(accountName, events, oldEvents)

}