package com.github.rozyhead.balmn.kanban.domain.model.board

import com.github.rozyhead.balmn.common.domain.model.DomainEntity
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.sheet.*

data class Board(
    val id: BoardId = BoardId.generate(),
    val owner: BoardOwner = BoardOwner.empty,
    val name: BoardName = BoardName.empty
) : DomainEntity<BoardEvent, Board> {

  companion object {
    fun create(boardOwner: BoardOwner, boardName: BoardName, occurredBy: UserId): Pair<Board, BoardEvent> {
      return Board() and BoardCreated(BoardId.generate(), boardOwner, boardName, occurredBy = occurredBy)
    }
  }

  fun addSheet(sheetName: SheetName, occurredBy: UserId): Pair<Sheet, SheetEvent> {
    return Sheet() and SheetAdded(id, SheetId.generate(), sheetName, occurredBy = occurredBy)
  }

  override fun apply(event: BoardEvent): Board = when (event) {
    is BoardCreated -> {
      copy(
          id = event.boardId,
          owner = event.boardOwner,
          name = event.boardName
      )
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
