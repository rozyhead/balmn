package com.github.rozyhead.balmn.domain.model.authentication

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEvent

interface PasswordAuthenticationEvent : DomainEvent

data class PasswordAuthenticationCreated(
    val accountName: AccountName,
    val encodedPassword: EncodedPassword
) : PasswordAuthenticationEvent

