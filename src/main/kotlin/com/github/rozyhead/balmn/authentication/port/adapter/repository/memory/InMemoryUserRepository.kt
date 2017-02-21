package com.github.rozyhead.balmn.authentication.port.adapter.repository.memory

import com.github.rozyhead.balmn.authentication.application.repository.UserRepository
import com.github.rozyhead.balmn.authentication.domain.model.User
import com.github.rozyhead.balmn.authentication.domain.model.UserEvent
import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper

class InMemoryUserRepository : UserRepository {

  val helper = InMemoryRepositoryHelper<UserEvent, User, UserId>(
      emptyEntity = User()
  )

  override fun exists(id: UserId): Boolean
      = helper.existsInMemory(id)

  override fun find(id: UserId): Pair<User, Version>?
      = helper.findByMemory(id)

  override fun save(id: UserId, version: Version, vararg additionalEvents: UserEvent)
      = helper.saveToMemory(id, version, *additionalEvents)

}