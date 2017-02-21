package com.github.rozyhead.balmn.kanban.domain.model.card

import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId
import java.time.LocalDateTime

interface CardEvent : DomainEvent {
  val cardId: CardId
  val occurredBy: UserId
}

data class CardAdded(
    val sheetId: SheetId,
    override val cardId: CardId,
    val cardTitle: CardTitle,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: UserId
) : CardEvent
