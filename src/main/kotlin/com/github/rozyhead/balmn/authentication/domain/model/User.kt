package com.github.rozyhead.balmn.authentication.domain.model

import com.github.rozyhead.balmn.common.domain.model.DomainEntity

data class User(
    val id: UserId = UserId.generate(),
    val name: UserName = UserName.empty,
    val authenticationMethods: Set<AuthenticationMethod> = emptySet()
) : DomainEntity<UserEvent, User> {

  companion object {
    fun create(userName: UserName, authenticationMethod: AuthenticationMethod): Pair<User, UserEvent> {
      return User() and UserCreated(UserId.generate(), userName, authenticationMethod)
    }
  }

  override fun apply(event: UserEvent): User = when (event) {
    is UserCreated -> {
      copy(
          id = event.userId,
          name = event.userName,
          authenticationMethods = setOf(event.authenticationMethod)
      )
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}