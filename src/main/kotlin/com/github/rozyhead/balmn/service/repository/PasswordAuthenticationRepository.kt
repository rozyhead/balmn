package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationEvent

interface PasswordAuthenticationRepository {

  fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, List<PasswordAuthenticationEvent>>?

  fun save(accountName: AccountName, events: List<PasswordAuthenticationEvent>, oldEvents: List<PasswordAuthenticationEvent>)

}

