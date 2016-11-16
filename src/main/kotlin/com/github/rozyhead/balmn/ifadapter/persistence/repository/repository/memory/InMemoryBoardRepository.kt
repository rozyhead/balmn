package com.github.rozyhead.balmn.ifadapter.persistence.repository.repository.memory

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

  override fun save(boardIdentifier: BoardIdentifier, events: List<BoardEvent>, oldEvents: List<BoardEvent>) {
    this.events[boardIdentifier] = oldEvents + events
  }

}