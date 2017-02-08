package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.Account
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.common.domain.model.DomainEntity

data class UserAccount(
    override val accountName: AccountName = AccountName("")
) : Account, DomainEntity<UserAccountEvent, UserAccount> {

  companion object {
    fun create(accountName: AccountName): Pair<UserAccount, UserAccountEvent> {
      return UserAccount() and UserAccountCreated(accountName)
    }
  }

  override fun allowBoardCreationForUser(accountName: AccountName): Boolean {
    return accountName == this.accountName
  }

  override infix fun apply(event: UserAccountEvent) = when (event) {
    is UserAccountCreated -> {
      copy(accountName = event.accountName)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}