package com.github.rozyhead.balmn.kanban.domain.model.comment

import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId

data class Comment(
    val id: CommentId = CommentId.generate(),
    val cardId: CardId = CardId.generate(),
    val content: CommentContent = CommentContent.empty
) : DomainEntity<CommentEvent, Comment> {

  override fun apply(event: CommentEvent): Comment = when (event) {
    is CommentAdded -> {
      copy(
          id = event.commentId,
          cardId = event.cardId,
          content = event.commentContent
      )
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}