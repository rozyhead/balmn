package com.github.rozyhead.balmn.account.domain.model.group

import com.github.rozyhead.balmn.account.domain.model.Account
import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.AccountType
import com.github.rozyhead.balmn.common.domain.model.DomainEntity

data class GroupAccount(
    override val id: AccountId,
    override val accountName: AccountName,
    val members: GroupMembers
) : Account, DomainEntity<GroupAccountEvent, GroupAccount> {

  override val type: AccountType
    get() = AccountType.GroupAccount

  override fun apply(event: GroupAccountEvent): GroupAccount {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}