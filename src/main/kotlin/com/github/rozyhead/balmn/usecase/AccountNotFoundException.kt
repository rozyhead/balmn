package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.AccountName

class AccountNotFoundException(val accountName: AccountName) : Exception(accountName.value)