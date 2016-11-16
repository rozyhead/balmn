package com.github.rozyhead.balmn.domain.model.authentication.password

import com.github.rozyhead.balmn.domain.model.account.AccountName

interface PasswordAuthenticationRepository {

  fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, List<PasswordAuthenticationEvent>>?

  fun save(accountName: AccountName, events: List<PasswordAuthenticationEvent>, oldEvents: List<PasswordAuthenticationEvent>)

}

