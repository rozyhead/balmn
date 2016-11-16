package com.github.rozyhead.balmn.ifadapter.persistence.repository.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.account.UserAccountEvent
import com.github.rozyhead.balmn.domain.model.account.UserAccountRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryUserAccountRepository : UserAccountRepository {

  val events = mutableMapOf<AccountName, List<UserAccountEvent>>()

  override fun findByAccountName(accountName: AccountName): Pair<UserAccount, List<UserAccountEvent>>? {
    val events = this.events[accountName] ?: return null
    return Pair(events.fold(UserAccount(), { a, b -> a apply b }), events)
  }

  override fun save(accountName: AccountName, events: List<UserAccountEvent>, oldEvents: List<UserAccountEvent>) {
    this.events[accountName] = oldEvents + events
  }

}