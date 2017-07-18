package com.github.rozyhead.balmn.kanban.port.adapter.repository.memory

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper
import com.github.rozyhead.balmn.kanban.domain.model.board.Board
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardRepository

class InMemoryBoardRepository : BoardRepository {

  val helper = InMemoryRepositoryHelper<BoardEvent, Board, BoardId>(
      emptyEntity = Board()
  )

  override fun find(id: BoardId): Pair<Board, Version>?
      = helper.findByMemory(id)

  override fun save(id: BoardId, version: Version, vararg additionalEvents: BoardEvent)
      = helper.saveToMemory(id, version, *additionalEvents)

}