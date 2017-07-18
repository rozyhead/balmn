package com.github.rozyhead.balmn.account.domain.model

sealed class AccountOperationException(message: String) : Exception(message) {

  class AccountNameAlreadyUsedException(accountName: AccountName)
    : AccountOperationException(accountName.value)

  companion object {

    fun accountNameAlreadyUsed(accountName: AccountName): AccountNameAlreadyUsedException {
      return AccountNameAlreadyUsedException(accountName)
    }

  }
}