package com.github.rozyhead.balmn.account.domain.model.authentication.password

import com.github.rozyhead.balmn.account.domain.model.authentication.AuthenticationMethod

data class PasswordAuthentication(
    val encodedPassword: EncodedPassword
) : AuthenticationMethod {

  companion object {
    fun fromPlainPassword(plainPassword: PlainPassword): PasswordAuthentication {
      val encodedPassword = EncodedPassword.encode(plainPassword)
      return PasswordAuthentication(encodedPassword)
    }
  }

}
