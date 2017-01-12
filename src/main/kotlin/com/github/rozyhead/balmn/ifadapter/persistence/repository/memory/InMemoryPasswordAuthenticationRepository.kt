package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.service.repository.PasswordAuthenticationRepository

class InMemoryPasswordAuthenticationRepository : PasswordAuthenticationRepository, AbstractInMemoryRepository<PasswordAuthenticationEvent, PasswordAuthentication, AccountName>() {

  override val emptyEntity: PasswordAuthentication
    get() = PasswordAuthentication()

  override fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, List<PasswordAuthenticationEvent>>? = findByMemory(accountName)

  override fun save(accountName: AccountName, events: List<PasswordAuthenticationEvent>, oldEvents: List<PasswordAuthenticationEvent>) = saveToMemory(accountName, events, oldEvents)

}