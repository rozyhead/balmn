package com.github.rozyhead.balmn.board.domain.model

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import java.time.LocalDateTime

interface BoardEvent : DomainEvent {
  val occurredBy: AccountName
}

data class BoardCreated(
    val accountName: AccountName,
    val boardName: BoardName,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : BoardEvent

data class SheetAdded(
    val accountName: AccountName,
    val boardName: BoardName,
    val sheetId: SheetId,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : BoardEvent