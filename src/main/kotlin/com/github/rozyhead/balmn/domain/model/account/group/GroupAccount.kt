package com.github.rozyhead.balmn.domain.model.account.group

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName

data class GroupAccount(
    override val accountName: AccountName,
    val members: GroupMembers
) : Account {

  override fun allowBoardCreationForUser(accountName: AccountName): Boolean {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}