package com.github.rozyhead.balmn.domain.model.account.user

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEntity

data class UserAccount(
    override val accountName: AccountName = AccountName("")
) : Account, DomainEntity<UserAccountEvent, UserAccount> {

  companion object {
    fun create(accountName: AccountName): Pair<UserAccount, UserAccountCreated> {
      return UserAccount() and UserAccountCreated(accountName)
    }
  }

  override fun allowBoardCreationForUser(accountName: AccountName): Boolean {
    return accountName == this.accountName
  }

  override infix fun <E> apply(event: E) = when (event) {
    is UserAccountCreated -> {
      copy(accountName = event.accountName)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}