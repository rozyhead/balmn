package com.github.rozyhead.balmn.authentication.port.adapter.repository.memory

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.authentication.application.repository.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthentication
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper

class InMemoryPasswordAuthenticationRepository : PasswordAuthenticationRepository {

  val helper = InMemoryRepositoryHelper<PasswordAuthenticationEvent, PasswordAuthentication, AccountName>(
      emptyEntity = PasswordAuthentication()
  )

  override fun exists(accountName: AccountName): Boolean = helper.existsInMemory(accountName)

  override fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, Version>?
      = helper.findByMemory(accountName)

  override fun save(accountName: AccountName, version: Version, vararg additionalEvents: PasswordAuthenticationEvent)
      = helper.saveToMemory(accountName, version, *additionalEvents)

}