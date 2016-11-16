package com.github.rozyhead.balmn.domain.model.account.user

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import java.time.LocalDateTime

interface UserAccountEvent : DomainEvent

data class UserAccountCreated(
    val accountName: AccountName,
    override val occurredOn: LocalDateTime = LocalDateTime.now()
) : UserAccountEvent

