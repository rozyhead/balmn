package com.github.rozyhead.balmn.board.domain.model.card

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import java.time.LocalDateTime

interface CardEvent : DomainEvent {
  val occurredBy: AccountName
}

data class CardCreated(
    val cardId: CardId,
    val cardTitle: CardTitle,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : CardEvent

