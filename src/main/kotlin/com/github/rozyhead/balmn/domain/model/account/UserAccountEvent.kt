package com.github.rozyhead.balmn.domain.model.account

import com.github.rozyhead.balmn.util.ddd.DomainEvent

interface UserAccountEvent : DomainEvent

data class UserAccountCreated(
    val accountName: AccountName
) : UserAccountEvent

