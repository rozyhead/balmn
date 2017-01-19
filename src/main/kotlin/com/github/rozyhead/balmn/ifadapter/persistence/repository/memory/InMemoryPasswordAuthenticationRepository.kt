package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.service.repository.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.util.ddd.Version

class InMemoryPasswordAuthenticationRepository : PasswordAuthenticationRepository, AbstractInMemoryRepository<PasswordAuthenticationEvent, PasswordAuthentication, AccountName>() {

  override val emptyEntity: PasswordAuthentication
    get() = PasswordAuthentication()

  override fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, Version>?
      = findByMemory(accountName)

  override fun save(accountName: AccountName, version: Version, vararg additionalEvents: PasswordAuthenticationEvent) = saveToMemory(accountName, version, *additionalEvents)

}