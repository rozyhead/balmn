package com.github.rozyhead.balmn.kanban.domain.model

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.domain.model.board.*
import org.springframework.stereotype.Service

@Service
class CreateNewBoardService(
    val boardOwnerService: BoardOwnerService,
    val boardGrantService: BoardGrantService,
    val boardNameIndex: BoardNameIndex,
    val boardRepository: BoardRepository
) {

  @Throws(BoardOperationException::class)
  fun createNewBoard(boardOwner: BoardOwner, boardName: BoardName, requestedBy: UserId): BoardId {

    // ボードオーナーの存在チェック
    checkBoardOwnerExists(boardOwner)

    // ボードの作成権限チェック
    checkGrant(boardOwner, requestedBy)

    // ボード名の存在チェック
    checkBoardNameNotExists(boardOwner, boardName)

    // 新規ボードを作成して保存
    val (board, boardEvent) = Board.create(boardOwner, boardName, requestedBy)
    boardRepository.save(board.id, Version.zero, boardEvent)

    // ボード名インデックスに保存
    boardNameIndex.save(boardOwner, boardName, board.id)

    return board.id
  }

  private fun checkBoardOwnerExists(boardOwner: BoardOwner) {
    if (!boardOwnerService.exists(boardOwner)) {
      throw BoardOperationException.boardOwnerNotFound(boardOwner)
    }
  }

  private fun checkGrant(boardOwner: BoardOwner, userId: UserId) {
    if (!boardGrantService.allowBoardCreationByUser(boardOwner, userId)) {
      throw BoardOperationException.boardOperationNotAllowed(boardOwner)
    }
  }

  private fun checkBoardNameNotExists(boardOwner: BoardOwner, boardName: BoardName) {
    if (boardNameIndex.exists(boardOwner, boardName)) {
      throw BoardOperationException.boardAlreadyExists(boardOwner, boardName)
    }
  }

}

