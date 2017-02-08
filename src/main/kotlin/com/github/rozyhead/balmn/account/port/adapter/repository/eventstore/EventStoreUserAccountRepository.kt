package com.github.rozyhead.balmn.account.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStoreUserAccountRepository(eventStore: EventStore) : UserAccountRepository {

  val helper = EventStoreRepositoryHelper<UserAccountEvent, UserAccount, AccountName>(
      eventStore = eventStore,
      eventClass = UserAccountEvent::class,
      emptyEntity = UserAccount(),
      streamIdOf = { "UserAccount(${it.value}" }
  )

  override fun exists(accountName: AccountName): Boolean = helper.existsInStore(accountName)

  override fun findByAccountName(accountName: AccountName): Pair<UserAccount, Version>?
      = helper.findByStore(accountName)

  override fun save(accountName: AccountName, version: Version, vararg additionalEvents: UserAccountEvent)
      = helper.saveToStore(accountName, version, *additionalEvents)

}