package com.github.rozyhead.balmn.domain.model.authentication.password

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import java.time.LocalDateTime

interface PasswordAuthenticationEvent : DomainEvent

data class PasswordAuthenticationCreated(
    val accountName: AccountName,
    val encodedPassword: EncodedPassword,
    override val occurredOn: LocalDateTime = LocalDateTime.now()
) : PasswordAuthenticationEvent

