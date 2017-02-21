package com.github.rozyhead.balmn.account.domain.model

sealed class AccountType {
  object UserAccount : AccountType()
  object GroupAccount : AccountType()
}
