package com.github.rozyhead.balmn.account.domain.model

interface Account {
  val id: AccountId
  val accountName: AccountName
  val type: AccountType
}
