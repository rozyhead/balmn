package com.github.rozyhead.balmn.domain.model.authentication.password

data class PlainPassword(val value: String) {

  fun encode(): EncodedPassword = EncodedPassword.encode(this)

}
