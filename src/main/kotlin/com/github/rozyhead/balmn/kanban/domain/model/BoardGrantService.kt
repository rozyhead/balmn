package com.github.rozyhead.balmn.kanban.domain.model

import com.github.rozyhead.balmn.kanban.domain.model.board.Board
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwner
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwnerService
import org.springframework.stereotype.Service

@Service
class BoardGrantService(
    val boardOwnerService: BoardOwnerService
) {

  fun allowBoardCreationByUser(boardOwner: BoardOwner, userId: UserId): Boolean {
    return boardOwnerService.isMember(boardOwner, userId)
  }

  fun allowSheetAdditionByUser(board: Board, userId: UserId): Boolean {
    return boardOwnerService.isMember(board.owner, userId)
  }

  fun allowCardAdditionByUser(board: Board, userId: UserId): Boolean {
    return boardOwnerService.isMember(board.owner, userId)
  }

  fun allowCommentAdditionByUser(board: Board, userId: UserId): Boolean {
    return boardOwnerService.isMember(board.owner, userId)
  }

}

