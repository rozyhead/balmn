package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier
import com.github.rozyhead.balmn.domain.model.board.comment.CommentIdentifier
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import java.time.LocalDateTime

interface CommentEvent : DomainEvent {
  val occurredBy: AccountName
}

data class CommentCreated(
    val commentIdentifier: CommentIdentifier,
    val cardIdentifier: CardIdentifier,
    override val occurredOn: LocalDateTime = LocalDateTime.now(),
    override val occurredBy: AccountName
) : CommentEvent

