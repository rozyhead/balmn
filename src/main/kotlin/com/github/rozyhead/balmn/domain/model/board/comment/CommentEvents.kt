package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.domain.model.board.comment.CommentId
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import java.time.LocalDateTime

interface CommentEvent : DomainEvent {
  val occurredBy: AccountName
}

data class CommentCreated(
    val commentId: CommentId,
    val cardId: CardId,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : CommentEvent

