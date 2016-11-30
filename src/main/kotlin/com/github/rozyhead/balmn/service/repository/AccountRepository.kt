package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName

interface AccountRepository {

  fun findByAccountName(accountName: AccountName): Account?

  fun exists(accountName: AccountName): Boolean

}