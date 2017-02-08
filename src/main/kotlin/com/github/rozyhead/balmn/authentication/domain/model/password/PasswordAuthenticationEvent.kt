package com.github.rozyhead.balmn.authentication.domain.model.password

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import java.time.LocalDateTime

interface PasswordAuthenticationEvent : DomainEvent

data class PasswordAuthenticationCreated(
    val accountName: AccountName,
    val encodedPassword: EncodedPassword,
    override val occurredOn: LocalDateTime = LocalDateTime.now()
) : PasswordAuthenticationEvent

