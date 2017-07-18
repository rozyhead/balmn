package com.github.rozyhead.balmn.kanban.domain.model.board

import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import java.time.LocalDateTime

interface BoardEvent : DomainEvent {
  val boardId: BoardId
  val occurredBy: UserId
}

data class BoardCreated(
    override val boardId: BoardId,
    val boardOwner: BoardOwner,
    val boardName: BoardName,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: UserId
) : BoardEvent
