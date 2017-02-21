package com.github.rozyhead.balmn.authentication.port.adapter.index.memory

import com.github.rozyhead.balmn.authentication.application.index.UserNameIndex
import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.authentication.domain.model.UserName
import org.springframework.stereotype.Component

@Component
class InMemoryUserNameIndex : UserNameIndex {

  val map = mutableMapOf<UserName, UserId>()

  override fun exists(userName: UserName): Boolean {
    return map.containsKey(userName)
  }

  override fun find(userName: UserName): UserId? {
    return map[userName]
  }

  override fun save(userName: UserName, userId: UserId) {
    map[userName] = userId
  }

  override fun delete(userName: UserName) {
    map.remove(userName)
  }

}