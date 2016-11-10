package com.github.rozyhead.balmn.domain.model.authentication

import com.github.rozyhead.balmn.domain.model.account.AccountName

data class PasswordAuthentication(
    val accountName: AccountName,
    val encodedPassword: EncodedPassword
) {

  fun authenticate(accountName: AccountName, plainPassword: PlainPassword): Boolean =
      accountName == this.accountName && encodedPassword.matches(plainPassword)

}
