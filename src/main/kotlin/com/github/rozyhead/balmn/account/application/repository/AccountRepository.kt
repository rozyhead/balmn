package com.github.rozyhead.balmn.account.application.repository

import com.github.rozyhead.balmn.account.domain.model.Account
import com.github.rozyhead.balmn.account.domain.model.AccountName

interface AccountRepository {

  fun findByAccountName(accountName: AccountName): Account?

  fun exists(accountName: AccountName): Boolean

}