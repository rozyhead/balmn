package com.github.rozyhead.balmn.domain.model.authentication

import com.github.rozyhead.balmn.domain.model.account.AccountName

interface PasswordAuthenticationRepository {

  fun findByAccountName(accountName: AccountName): PasswordAuthentication?

  fun save(passwordAuthentication: PasswordAuthentication)

}

