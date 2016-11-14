package com.github.rozyhead.balmn.ifadapter.repository.memory

import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryBoardRepository : BoardRepository {

  val entities = mutableMapOf<BoardIdentifier, Board>()

  override fun exists(boardIdentifier: BoardIdentifier): Boolean {
    return entities.containsKey(boardIdentifier)
  }

  override fun save(board: Board) {
    entities[board.identifier] = board
  }

}