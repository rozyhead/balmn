package com.github.rozyhead.balmn.kanban.domain.model.sheet

import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId
import java.time.LocalDateTime

interface SheetEvent : DomainEvent {
  val sheetId: SheetId
  val occurredBy: UserId
}

data class SheetAdded(
    val boardId: BoardId,
    override val sheetId: SheetId,
    val sheetName: SheetName,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: UserId
) : SheetEvent
