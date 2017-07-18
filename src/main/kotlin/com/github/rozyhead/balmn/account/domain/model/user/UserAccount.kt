package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.Account
import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.AccountType
import com.github.rozyhead.balmn.account.domain.model.authentication.AuthenticationMethod
import com.github.rozyhead.balmn.common.domain.model.DomainEntity

data class UserAccount(
    override val id: AccountId = AccountId.generate(),
    override val accountName: AccountName = AccountName(""),
    val authenticationMethod: AuthenticationMethod = AuthenticationMethod.Empty
) : Account, DomainEntity<UserAccountEvent, UserAccount> {

  companion object {
    fun create(accountName: AccountName, authenticationMethod: AuthenticationMethod): Pair<UserAccount, UserAccountEvent> {
      return UserAccount() and UserAccountCreated(AccountId.generate(), accountName, authenticationMethod)
    }
  }

  override val type: AccountType
    get() = AccountType.UserAccount

  override infix fun apply(event: UserAccountEvent) = when (event) {
    is UserAccountCreated -> {
      copy(id = event.accountId, accountName = event.accountName, authenticationMethod = event.authenticationMethod)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}