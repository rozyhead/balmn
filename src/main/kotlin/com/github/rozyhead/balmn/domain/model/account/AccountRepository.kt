package com.github.rozyhead.balmn.domain.model.account

interface AccountRepository {

  fun findByAccountName(accountName: AccountName): Account?

  fun exists(accountName: AccountName): Boolean

}