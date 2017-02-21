package com.github.rozyhead.balmn.kanban.domain.model.card

import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.comment.*
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId

data class Card(
    val id: CardId = CardId.generate(),
    val sheetId: SheetId = SheetId.generate(),
    val title: CardTitle = CardTitle.empty
) : DomainEntity<CardEvent, Card> {

  fun addComment(content: CommentContent, occurredBy: UserId): Pair<Comment, CommentEvent> {
    return Comment() and CommentAdded(id, CommentId.generate(), content, occurredBy = occurredBy)
  }

  override fun apply(event: CardEvent): Card = when (event) {
    is CardAdded -> {
      copy(
          id = event.cardId,
          sheetId = event.sheetId,
          title = event.cardTitle
      )
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
