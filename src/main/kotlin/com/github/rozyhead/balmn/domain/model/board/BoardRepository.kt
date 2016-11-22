package com.github.rozyhead.balmn.domain.model.board

interface BoardRepository {

  fun exists(boardIdentifier: BoardIdentifier): Boolean

  fun findByIdentifier(boardIdentifier: BoardIdentifier): Pair<Board, List<BoardEvent>>?

  fun save(boardIdentifier: BoardIdentifier, events: List<BoardEvent>, oldEvents: List<BoardEvent>)

}