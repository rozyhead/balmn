package com.github.rozyhead.balmn.kanban.domain.model.sheet

import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId
import com.github.rozyhead.balmn.kanban.domain.model.card.*

data class Sheet(
    val id: SheetId = SheetId.generate(),
    val boardId: BoardId = BoardId.generate(),
    val name: SheetName = SheetName("")
) : DomainEntity<SheetEvent, Sheet> {

  fun addCard(cardTitle: CardTitle, occurredBy: UserId): Pair<Card, CardEvent> {
    return Card() and CardAdded(id, CardId.generate(), cardTitle, occurredBy = occurredBy)
  }

  override fun apply(event: SheetEvent): Sheet = when (event) {
    is SheetAdded -> {
      copy(
          id = event.sheetId,
          boardId = event.boardId,
          name = event.sheetName
      )
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
