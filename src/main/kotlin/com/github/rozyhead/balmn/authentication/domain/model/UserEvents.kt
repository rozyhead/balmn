package com.github.rozyhead.balmn.authentication.domain.model

import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import java.time.LocalDateTime

interface UserEvent : DomainEvent {
  val userId: UserId
}

data class UserCreated(
    override val userId: UserId,
    val userName: UserName,
    val authenticationMethod: AuthenticationMethod,
    override val occurredOn: LocalDateTime = LocalDateTime.now()
) : UserEvent

