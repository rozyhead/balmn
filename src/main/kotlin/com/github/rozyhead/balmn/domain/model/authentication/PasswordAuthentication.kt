package com.github.rozyhead.balmn.domain.model.authentication

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEntity

data class PasswordAuthentication(
    val accountName: AccountName = AccountName(""),
    val encodedPassword: EncodedPassword = EncodedPassword("")
) : DomainEntity<PasswordAuthenticationEvent, PasswordAuthentication> {

  companion object {
    fun create(accountName: AccountName, plainPassword: PlainPassword) : Pair<PasswordAuthentication, PasswordAuthenticationCreated> {
      return PasswordAuthentication() and PasswordAuthenticationCreated(accountName, plainPassword.encode())
    }
  }

  fun authenticate(accountName: AccountName, plainPassword: PlainPassword): Boolean =
      accountName == this.accountName && encodedPassword.matches(plainPassword)

  override infix fun <E> apply(event: E) = when (event) {
    is PasswordAuthenticationCreated -> {
      copy(accountName = event.accountName, encodedPassword = event.encodedPassword)
    }
    else -> throw IllegalArgumentException(event.toString())
  }

}
