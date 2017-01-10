package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.UserAccountRepository
import org.springframework.stereotype.Repository
import kotlin.reflect.KClass

@Repository
class EventStoreUserAccountRepository(
    eventStore: EventStore
) : UserAccountRepository, AbstractEventStoreRepository<UserAccountEvent, UserAccount>(eventStore) {

  override val eventClass: KClass<UserAccountEvent>
    get() = UserAccountEvent::class

  override val emptyEntity: UserAccount
    get() = UserAccount()

  fun streamId(accountName: AccountName) = "UserAccount(${accountName.value}"

  override fun findByAccountName(accountName: AccountName): Pair<UserAccount, List<UserAccountEvent>>? = findByStore(streamId(accountName))

  override fun save(accountName: AccountName, events: List<UserAccountEvent>, oldEvents: List<UserAccountEvent>) = saveToStore(streamId(accountName), events, oldEvents)

}