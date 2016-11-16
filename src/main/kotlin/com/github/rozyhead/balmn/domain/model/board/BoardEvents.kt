package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier
import com.github.rozyhead.balmn.util.ddd.DomainEvent

interface BoardEvent : DomainEvent

data class BoardCreated(
    val boardIdentifier: BoardIdentifier,
    val createdBy: AccountName
) : BoardEvent

data class SheetAdded(
    val boardIdentifier: BoardIdentifier,
    val sheetIdentifier: SheetIdentifier,
    val addedBy: AccountName
)