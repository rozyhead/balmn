package com.github.rozyhead.balmn.account.domain.model.authentication.password

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class EncodedPassword(val value: String) {

  companion object {

    private val encoder = BCryptPasswordEncoder()

    fun encode(plainPassword: PlainPassword): EncodedPassword {
      val encoded = encoder.encode(plainPassword.value)
      return EncodedPassword(encoded)
    }

  }

  fun matches(plainPassword: PlainPassword): Boolean = encoder.matches(plainPassword.value, this.value)

}
