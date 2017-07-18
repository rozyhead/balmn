package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.authentication.AuthenticationMethod
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import java.time.LocalDateTime

interface UserAccountEvent : DomainEvent {
  val accountId: AccountId
}

data class UserAccountCreated(
    override val accountId: AccountId,
    val accountName: AccountName,
    val authenticationMethod: AuthenticationMethod,
    override val occurredOn: LocalDateTime = LocalDateTime.now()
) : UserAccountEvent

