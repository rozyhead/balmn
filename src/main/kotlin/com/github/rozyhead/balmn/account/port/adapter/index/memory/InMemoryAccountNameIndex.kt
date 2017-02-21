package com.github.rozyhead.balmn.account.port.adapter.index.memory

import com.github.rozyhead.balmn.account.application.index.AccountNameIndex
import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.AccountType
import org.springframework.stereotype.Component

@Component
class InMemoryAccountNameIndex : AccountNameIndex {

  val index = mutableMapOf<AccountName, Pair<AccountId, AccountType>>()

  override fun exists(accountName: AccountName): Boolean {
    return index.containsKey(accountName)
  }

  override fun find(accountName: AccountName): Pair<AccountId, AccountType>? {
    return index[accountName]
  }

  override fun save(accountName: AccountName, accountId: AccountId, accountType: AccountType) {
    index[accountName] = Pair(accountId, accountType)
  }

  override fun delete(accountName: AccountName) {
    index.remove(accountName)
  }

}