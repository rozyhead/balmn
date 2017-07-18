package com.github.rozyhead.balmn.kanban.domain.model.board

interface BoardNameIndex {

  fun exists(boardOwner: BoardOwner, boardName: BoardName): Boolean

  fun find(boardOwner: BoardOwner, boardName: BoardName): BoardId?

  fun save(boardOwner: BoardOwner, boardName: BoardName, boardId: BoardId)

}