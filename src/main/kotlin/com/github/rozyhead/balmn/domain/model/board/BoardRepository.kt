package com.github.rozyhead.balmn.domain.model.board

interface BoardRepository {

  fun exists(boardId: BoardId): Boolean

  fun findById(boardId: BoardId): Pair<Board, List<BoardEvent>>?

  fun save(boardId: BoardId, events: List<BoardEvent>, oldEvents: List<BoardEvent>)

}