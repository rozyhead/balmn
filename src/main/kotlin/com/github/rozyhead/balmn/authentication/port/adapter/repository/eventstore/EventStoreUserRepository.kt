package com.github.rozyhead.balmn.authentication.port.adapter.repository.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.msemys.esjc.EventStore
import com.github.rozyhead.balmn.authentication.domain.model.User
import com.github.rozyhead.balmn.authentication.domain.model.UserEvent
import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.authentication.domain.model.UserRepository
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper

class EventStoreUserRepository(
    eventStore: EventStore,
    objectMapper: ObjectMapper
) : UserRepository {

  val helper = EventStoreRepositoryHelper<UserEvent, User, UserId>(
      eventStore = eventStore,
      objectMapper = objectMapper,
      emptyEntity = User(),
      streamIdOf = { "User-${it.uuid}" }
  )

  override fun find(id: UserId): Pair<User, Version>?
      = helper.findByStore(id)

  override fun save(id: UserId, version: Version, vararg additionalEvents: UserEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}