package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryBoardRepository : BoardRepository {

  val events = mutableMapOf<BoardIdentifier, List<BoardEvent>>()

  override fun exists(boardIdentifier: BoardIdentifier): Boolean {
    return events.contains(boardIdentifier)
  }

  override fun findByIdentifier(boardIdentifier: BoardIdentifier): Pair<Board, List<BoardEvent>>? {
    val events = this.events[boardIdentifier] ?: return null
    return Pair(events.fold(Board(), { a, b -> a apply b }), events)
  }

  override fun save(boardIdentifier: BoardIdentifier, events: List<BoardEvent>, oldEvents: List<BoardEvent>) {
    this.events[boardIdentifier] = oldEvents + events
  }

}