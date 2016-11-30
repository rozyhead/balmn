package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardId

interface BoardRepository {

  fun exists(boardId: BoardId): Boolean

  fun findById(boardId: BoardId): Pair<Board, List<BoardEvent>>?

  fun save(boardId: BoardId, events: List<BoardEvent>, oldEvents: List<BoardEvent>)

}