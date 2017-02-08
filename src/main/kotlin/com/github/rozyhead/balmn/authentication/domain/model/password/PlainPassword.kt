package com.github.rozyhead.balmn.authentication.domain.model.password

data class PlainPassword(val value: String) {

  fun encode(): EncodedPassword = EncodedPassword.encode(this)

}
