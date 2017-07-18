package com.github.rozyhead.balmn.kanban.domain.model

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.domain.model.board.*
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetName
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetRepository
import org.springframework.stereotype.Service

@Service
class AddSheetService(
    val boardGrantService: BoardGrantService,
    val boardNameIndex: BoardNameIndex,
    val sheetRepository: SheetRepository,
    val boardRepository: BoardRepository
) {

  @Throws(BoardOperationException::class)
  fun addSheet(boardOwner: BoardOwner, boardName: BoardName, sheetName: SheetName, requestedBy: UserId): SheetId {

    val boardId = boardNameIndex.find(boardOwner, boardName)
        ?: throw BoardOperationException.boardNotFound(boardOwner, boardName)

    val (board) = boardRepository.find(boardId)
        ?: throw BoardOperationException.boardNotFound(boardOwner, boardName)

    // シートの追加権限チェック
    checkGrant(board, requestedBy)

    val (sheet, sheetEvent) = board.addSheet(sheetName, requestedBy)
    sheetRepository.save(sheet.id, Version.zero, sheetEvent)

    return sheet.id
  }

  private fun checkGrant(board: Board, userId: UserId) {
    if (!boardGrantService.allowSheetAdditionByUser(board, userId)) {
      throw BoardOperationException.boardOperationNotAllowed(board.owner, board.name)
    }
  }

}