package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import java.time.LocalDateTime

interface SheetEvent : DomainEvent {
  val occurredBy: AccountName
}

data class SheetCreated(
    val sheetId: SheetId,
    val sheetName: SheetName,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : SheetEvent

data class CardAdded(
    val sheetId: SheetId,
    var cardId: CardId,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : SheetEvent
