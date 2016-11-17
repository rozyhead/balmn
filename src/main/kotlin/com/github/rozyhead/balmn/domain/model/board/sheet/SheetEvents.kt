package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import java.time.LocalDateTime

interface SheetEvent : DomainEvent {
  val occurredBy: AccountName
}

data class SheetCreated(
    val sheetIdentifier: SheetIdentifier,
    val sheetName: SheetName,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : SheetEvent

data class CardAdded(
    val sheetIdentifier: SheetIdentifier,
    var cardIdentifier: CardIdentifier,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : SheetEvent
