package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.util.ddd.DomainEvent
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