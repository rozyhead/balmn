package com.github.rozyhead.balmn.domain.model.account

data class GroupAccount(
    override val accountName: AccountName,
    val members: GroupMembers
) : Account {

  override fun creatableBoardByUser(accountName: AccountName): Boolean {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}