package com.github.rozyhead.balmn.account.domain.model.group

import com.github.rozyhead.balmn.account.domain.model.Account
import com.github.rozyhead.balmn.account.domain.model.AccountName

data class GroupAccount(
    override val accountName: AccountName,
    val members: GroupMembers
) : Account {

  override fun allowBoardCreationForUser(accountName: AccountName): Boolean {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}