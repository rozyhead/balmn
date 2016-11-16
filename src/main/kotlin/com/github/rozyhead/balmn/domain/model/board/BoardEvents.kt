package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEvent

interface BoardEvent : DomainEvent

data class BoardCreated(
    val identifier: BoardIdentifier,
    val createdBy: AccountName
) : BoardEvent

