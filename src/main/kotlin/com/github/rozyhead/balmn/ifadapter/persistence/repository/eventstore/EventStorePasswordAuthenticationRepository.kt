package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationEvent
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.util.ddd.Version

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