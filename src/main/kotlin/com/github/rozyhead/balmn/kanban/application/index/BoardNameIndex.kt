package com.github.rozyhead.balmn.kanban.application.index

import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardName
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwner

interface BoardNameIndex {

  fun exists(boardOwner: BoardOwner, boardName: BoardName): Boolean

  fun find(boardOwner: BoardOwner, boardName: BoardName): BoardId?

}