package com.github.rozyhead.balmn.domain.model.board.card

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import java.time.LocalDateTime

interface CardEvent : DomainEvent {
  val occurredBy: AccountName
}

data class CardCreated(
    val cardIdentifier: CardIdentifier,
    val cardTitle: CardTitle,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : CardEvent

