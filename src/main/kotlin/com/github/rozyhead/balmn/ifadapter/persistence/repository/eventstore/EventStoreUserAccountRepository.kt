package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.UserAccountRepository
import com.github.rozyhead.balmn.util.ddd.Version
import org.springframework.stereotype.Repository
import kotlin.reflect.KClass

@Repository
class EventStoreUserAccountRepository(
    eventStore: EventStore
) : UserAccountRepository, AbstractEventStoreRepository<UserAccountEvent, UserAccount, AccountName>(eventStore) {

  override val eventClass: KClass<UserAccountEvent>
    get() = UserAccountEvent::class

  override val emptyEntity: UserAccount
    get() = UserAccount()

  override fun streamIdOf(entityId: AccountName): String = "UserAccount(${entityId.value})"

  override fun findByAccountName(accountName: AccountName): Pair<UserAccount, Version>?
      = findByStore(accountName)

  override fun save(accountName: AccountName, version: Version, vararg additionalEvents: UserAccountEvent)
      = saveToStore(accountName, version, *additionalEvents)

}