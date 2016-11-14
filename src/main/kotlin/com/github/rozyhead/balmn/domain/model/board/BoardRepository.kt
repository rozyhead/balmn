package com.github.rozyhead.balmn.domain.model.board

interface BoardRepository {

  fun exists(boardIdentifier: BoardIdentifier): Boolean

  fun save(board: Board)

}