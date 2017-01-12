package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardId
import com.github.rozyhead.balmn.service.repository.BoardRepository

class InMemoryBoardRepository : BoardRepository, AbstractInMemoryRepository<BoardEvent, Board, BoardId>() {

  override val emptyEntity: Board
    get() = Board()

  override fun exists(boardId: BoardId): Boolean = existsInMemory(boardId)

  override fun findById(boardId: BoardId): Pair<Board, List<BoardEvent>>? = findByMemory(boardId)

  override fun save(boardId: BoardId, events: List<BoardEvent>, oldEvents: List<BoardEvent>) = saveToMemory(boardId, events, oldEvents)

}