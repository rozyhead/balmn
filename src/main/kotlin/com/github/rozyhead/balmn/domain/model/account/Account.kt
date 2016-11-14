package com.github.rozyhead.balmn.domain.model.account

interface Account {
  val accountName: AccountName

  fun creatableBoardByUser(accountName: AccountName): Boolean
}
