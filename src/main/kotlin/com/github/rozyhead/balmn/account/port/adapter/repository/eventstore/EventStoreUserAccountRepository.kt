package com.github.rozyhead.balmn.account.port.adapter.repository.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.msemys.esjc.EventStore
import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountEvent
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountRepository
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper

class EventStoreUserAccountRepository(
    eventStore: EventStore,
    objectMapper: ObjectMapper
) : UserAccountRepository {

  val helper = EventStoreRepositoryHelper<UserAccountEvent, UserAccount, AccountId>(
      eventStore = eventStore,
      objectMapper = objectMapper,
      emptyEntity = UserAccount(),
      streamIdOf = { "UserAccount-${it.uuid}" }
  )

  override fun find(id: AccountId): Pair<UserAccount, Version>?
      = helper.findByStore(id)

  override fun save(id: AccountId, version: Version, vararg additionalEvents: UserAccountEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}