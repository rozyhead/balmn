package com.github.rozyhead.balmn.kanban.domain.model.comment

import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId
import java.time.LocalDateTime

interface CommentEvent : DomainEvent {
  val commentId: CommentId
  val occurredBy: UserId
}

data class CommentAdded(
    val cardId: CardId,
    override val commentId: CommentId,
    val commentContent: CommentContent,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: UserId
) : CommentEvent

