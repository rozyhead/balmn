package com.github.rozyhead.balmn.domain.model.board.comment

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentCreated
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import com.github.rozyhead.balmn.util.ddd.DomainEntity

data class Comment(
    val identifier: CommentIdentifier = CommentIdentifier.generate(),
    val cardIdentifier: CardIdentifier = CardIdentifier.generate()
) : DomainEntity<CommentEvent, Comment> {

  companion object {
    fun create(cardIdentifier: CardIdentifier, occurredBy: AccountName): Pair<Comment, CommentCreated> {
      return Comment() and CommentCreated(CommentIdentifier.generate(), cardIdentifier, occurredBy = occurredBy)
    }
  }

  override fun <E> apply(event: E): Comment = when (event) {
    is CommentCreated -> {
      copy(identifier = event.commentIdentifier, cardIdentifier = event.cardIdentifier)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}