package com.github.rozyhead.balmn.account.domain.model

data class AccountName(val value: String) {

  companion object {

    val empty = AccountName("")

  }
}
