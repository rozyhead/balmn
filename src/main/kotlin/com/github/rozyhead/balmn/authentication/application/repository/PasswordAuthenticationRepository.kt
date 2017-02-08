package com.github.rozyhead.balmn.authentication.application.repository

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthentication
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.common.domain.model.Version

interface PasswordAuthenticationRepository {

  fun exists(accountName: AccountName): Boolean

  fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, Version>?

  fun save(accountName: AccountName, version: Version, vararg additionalEvents: PasswordAuthenticationEvent)

}

