package com.github.rozyhead.balmn.authentication.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.authentication.application.repository.UserRepository
import com.github.rozyhead.balmn.authentication.domain.model.User
import com.github.rozyhead.balmn.authentication.domain.model.UserEvent
import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStoreUserRepository(eventStore: EventStore) : UserRepository {

  val helper = EventStoreRepositoryHelper<UserEvent, User, UserId>(
      eventStore = eventStore,
      eventClass = UserEvent::class,
      emptyEntity = User(),
      streamIdOf = { "User-${it.uuid}" }
  )

  override fun exists(id: UserId): Boolean
      = helper.existsInStore(id)

  override fun find(id: UserId): Pair<User, Version>?
      = helper.findByStore(id)

  override fun save(id: UserId, version: Version, vararg additionalEvents: UserEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}