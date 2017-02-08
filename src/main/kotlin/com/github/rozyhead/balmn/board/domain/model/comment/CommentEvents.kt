package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.domain.model.card.CardId
import com.github.rozyhead.balmn.board.domain.model.comment.CommentContent
import com.github.rozyhead.balmn.board.domain.model.comment.CommentId
import com.github.rozyhead.balmn.common.domain.model.DomainEvent
import java.time.LocalDateTime

interface CommentEvent : DomainEvent {
  val occurredBy: AccountName
}

data class CommentCreated(
    val commentId: CommentId,
    val cardId: CardId,
    val commentContent: CommentContent,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : CommentEvent

