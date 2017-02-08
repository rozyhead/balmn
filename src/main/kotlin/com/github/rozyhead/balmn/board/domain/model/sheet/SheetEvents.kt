package com.github.rozyhead.balmn.board.domain.model.sheet

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.domain.model.card.CardId
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
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
