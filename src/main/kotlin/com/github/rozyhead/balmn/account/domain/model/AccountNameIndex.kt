package com.github.rozyhead.balmn.account.domain.model

interface AccountNameIndex {

  fun save(accountName: AccountName, accountId: AccountId, accountType: AccountType)

  fun exists(accountName: AccountName): Boolean

  fun find(accountName: AccountName): Pair<AccountId, AccountType>?

}