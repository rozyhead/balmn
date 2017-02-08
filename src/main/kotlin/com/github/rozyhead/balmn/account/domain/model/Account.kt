package com.github.rozyhead.balmn.account.domain.model

interface Account {
  val accountName: AccountName

  fun allowBoardCreationForUser(accountName: AccountName): Boolean
}
