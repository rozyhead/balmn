package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.UserAccountRepository
import com.github.rozyhead.balmn.util.ddd.Version
import org.springframework.stereotype.Repository

@Repository
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