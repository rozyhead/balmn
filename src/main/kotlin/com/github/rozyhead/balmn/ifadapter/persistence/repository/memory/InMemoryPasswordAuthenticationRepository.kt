package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.service.repository.PasswordAuthenticationRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryPasswordAuthenticationRepository : PasswordAuthenticationRepository {

  val events = mutableMapOf<AccountName, List<PasswordAuthenticationEvent>>()

  override fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, List<PasswordAuthenticationEvent>>? {
    val events = this.events[accountName] ?: return null
    return Pair(events.fold(PasswordAuthentication(), { a, b -> a apply b }), events)
  }

  override fun save(accountName: AccountName, events: List<PasswordAuthenticationEvent>, oldEvents: List<PasswordAuthenticationEvent>) {
    this.events[accountName] = oldEvents + events
  }

}