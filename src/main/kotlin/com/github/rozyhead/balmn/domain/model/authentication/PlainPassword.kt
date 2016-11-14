package com.github.rozyhead.balmn.domain.model.authentication

data class PlainPassword(val value: String) {

  fun encode(): EncodedPassword = EncodedPassword.encode(this)

}
