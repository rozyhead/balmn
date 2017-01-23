package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.service.repository.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.util.ddd.Version

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