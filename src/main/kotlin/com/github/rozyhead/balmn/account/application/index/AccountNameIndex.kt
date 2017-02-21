package com.github.rozyhead.balmn.account.application.index

import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.AccountType

interface AccountNameIndex {

  fun exists(accountName: AccountName): Boolean

  fun find(accountName: AccountName): Pair<AccountId, AccountType>?

}