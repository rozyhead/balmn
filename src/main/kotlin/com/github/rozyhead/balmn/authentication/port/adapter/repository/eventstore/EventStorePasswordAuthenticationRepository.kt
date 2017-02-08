package com.github.rozyhead.balmn.authentication.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.authentication.application.repository.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthentication
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStorePasswordAuthenticationRepository(eventStore: EventStore) : PasswordAuthenticationRepository {

  val helper = EventStoreRepositoryHelper<PasswordAuthenticationEvent, PasswordAuthentication, AccountName>(
      eventStore = eventStore,
      eventClass = PasswordAuthenticationEvent::class,
      emptyEntity = PasswordAuthentication(),
      streamIdOf = { "PasswordAuthentication(${it.value}" }
  )

  override fun exists(accountName: AccountName): Boolean
      = helper.existsInStore(accountName)

  override fun findByAccountName(accountName: AccountName): Pair<PasswordAuthentication, Version>?
      = helper.findByStore(accountName)

  override fun save(accountName: AccountName, version: Version, vararg additionalEvents: PasswordAuthenticationEvent)
      = helper.saveToStore(accountName, version, *additionalEvents)

}