package com.github.rozyhead.balmn.domain.model.account

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName

data class GroupAccount(
    override val accountName: AccountName
) : Account {

  override fun creatableBoardByUser(accountName: AccountName): Boolean {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}