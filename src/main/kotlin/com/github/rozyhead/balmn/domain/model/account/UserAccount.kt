package com.github.rozyhead.balmn.domain.model.account

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName

data class UserAccount(
    override val accountName: AccountName
) : Account {

  override fun creatableBoardByUser(accountName: AccountName): Boolean {
    return accountName == this.accountName
  }

}