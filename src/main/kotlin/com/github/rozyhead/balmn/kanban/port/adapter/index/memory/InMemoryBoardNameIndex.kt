package com.github.rozyhead.balmn.kanban.port.adapter.index.memory

import com.github.rozyhead.balmn.kanban.application.index.BoardNameIndex
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardName
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwner
import org.springframework.stereotype.Component

@Component
class InMemoryBoardNameIndex : BoardNameIndex {

  var index = mapOf<Pair<BoardOwner, BoardName>, BoardId>()

  override fun exists(boardOwner: BoardOwner, boardName: BoardName): Boolean {
    return index.containsKey(Pair(boardOwner, boardName))
  }

  override fun find(boardOwner: BoardOwner, boardName: BoardName): BoardId? {
    return index[Pair(boardOwner, boardName)]
  }

  fun save(boardOwner: BoardOwner, boardName: BoardName, boardId: BoardId) {
    index += Pair(Pair(boardOwner, boardName), boardId)
  }

  fun delete(boardOwner: BoardOwner, boardName: BoardName) {
    index = index.filterKeys { it != Pair(boardOwner, boardName) }
  }

  fun delete(boardOwner: BoardOwner) {
    index = index.filterKeys { it.first != boardOwner }
  }

}
