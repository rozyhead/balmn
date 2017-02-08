package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import java.time.LocalDateTime

interface UserAccountEvent : DomainEvent

data class UserAccountCreated(
    val accountName: AccountName,
    override val occurredOn: LocalDateTime = LocalDateTime.now()
) : UserAccountEvent

