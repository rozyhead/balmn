package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardId
import com.github.rozyhead.balmn.service.repository.BoardRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryBoardRepository : BoardRepository {

  val events = mutableMapOf<BoardId, List<BoardEvent>>()

  override fun exists(boardId: BoardId): Boolean {
    return events.contains(boardId)
  }

  override fun findById(boardId: BoardId): Pair<Board, List<BoardEvent>>? {
    val events = this.events[boardId] ?: return null
    return Pair(events.fold(Board(), { a, b -> a apply b }), events)
  }

  override fun save(boardId: BoardId, events: List<BoardEvent>, oldEvents: List<BoardEvent>) {
    this.events[boardId] = oldEvents + events
  }

}