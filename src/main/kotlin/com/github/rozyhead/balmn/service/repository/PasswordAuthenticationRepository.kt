package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.util.ddd.Version

interface PasswordAuthenticationRepository {

  fun exists(accountName: AccountName): Boolean

  fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, Version>?

  fun save(accountName: AccountName, version: Version, vararg additionalEvents: PasswordAuthenticationEvent)

}

