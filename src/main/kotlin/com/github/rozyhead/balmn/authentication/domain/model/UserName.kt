package com.github.rozyhead.balmn.authentication.domain.model

data class UserName(val value: String) {

  companion object {
    val empty = UserName("")
  }

}