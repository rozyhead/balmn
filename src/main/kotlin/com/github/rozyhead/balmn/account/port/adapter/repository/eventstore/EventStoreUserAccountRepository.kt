package com.github.rozyhead.balmn.account.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountEvent
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStoreUserAccountRepository(eventStore: EventStore) : UserAccountRepository {

  val helper = EventStoreRepositoryHelper<UserAccountEvent, UserAccount, AccountId>(
      eventStore = eventStore,
      eventClass = UserAccountEvent::class,
      emptyEntity = UserAccount(),
      streamIdOf = { "UserAccount-${it.uuid}" }
  )

  override fun exists(id: AccountId): Boolean = helper.existsInStore(id)

  override fun find(id: AccountId): Pair<UserAccount, Version>?
      = helper.findByStore(id)

  override fun save(id: AccountId, version: Version, vararg additionalEvents: UserAccountEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}