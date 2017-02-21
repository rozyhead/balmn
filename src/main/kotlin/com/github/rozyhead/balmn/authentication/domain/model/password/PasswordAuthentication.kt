package com.github.rozyhead.balmn.authentication.domain.model.password

import com.github.rozyhead.balmn.authentication.domain.model.AuthenticationMethod

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
