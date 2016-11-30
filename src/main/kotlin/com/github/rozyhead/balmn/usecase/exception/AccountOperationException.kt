package com.github.rozyhead.balmn.usecase.exception

import com.github.rozyhead.balmn.domain.model.account.AccountName

sealed class AccountOperationException(message: String) : Exception(message) {

  class AccountNameAlreadyUsedException(accountName: AccountName)
    : AccountOperationException(accountName.value)

  companion object {

    fun accountNameAlreadyUsed(accountName: AccountName): AccountNameAlreadyUsedException {
      return AccountNameAlreadyUsedException(accountName)
    }

  }
}