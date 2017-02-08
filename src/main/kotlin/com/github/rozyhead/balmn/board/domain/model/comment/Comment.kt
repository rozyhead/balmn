package com.github.rozyhead.balmn.board.domain.model.comment

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.domain.model.card.CardId
import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentCreated
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent

data class Comment(
    val id: CommentId = CommentId.generate(),
    val cardId: CardId = CardId.generate(),
    val content: CommentContent = CommentContent.empty
) : DomainEntity<CommentEvent, Comment> {

  companion object {
    fun create(cardId: CardId, content: CommentContent, occurredBy: AccountName): Pair<Comment, CommentEvent> {
      return Comment() and CommentCreated(CommentId.Companion.generate(), cardId, content, occurredBy = occurredBy)
    }
  }

  override fun apply(event: CommentEvent): Comment = when (event) {
    is CommentCreated -> {
      copy(id = event.commentId, cardId = event.cardId)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}