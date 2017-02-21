package com.github.rozyhead.balmn.kanban.application.service

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.application.exception.BoardOperationException
import com.github.rozyhead.balmn.kanban.application.index.BoardNameIndex
import com.github.rozyhead.balmn.kanban.application.repository.BoardRepository
import com.github.rozyhead.balmn.kanban.application.repository.SheetRepository
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardName
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwner
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwnerService
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetName
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SheetService(
    val boardOwnerService: BoardOwnerService,
    val boardNameIndex: BoardNameIndex,
    val sheetRepository: SheetRepository,
    val boardRepository: BoardRepository
) {

  data class AddSheetCommand(
      val boardOwner: BoardOwner,
      val boardName: BoardName,
      val sheetName: SheetName,
      val requestedBy: UserId
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun addSheet(command: AddSheetCommand): SheetId {
    val (boardOwner, boardName, sheetName, requestedBy) = command

    val boardId = boardNameIndex.find(boardOwner, boardName)
        ?: throw BoardOperationException.boardNotFound(boardOwner, boardName)

    val (board) = boardRepository.find(boardId)
        ?: throw BoardOperationException.boardNotFound(boardOwner, boardName)

    if (!board.allowSheetAdditionByUser(boardOwnerService, requestedBy)) {
      throw BoardOperationException.boardOperationNotAllowed(boardOwner, boardName)
    }

    val (sheet, sheetEvent) = board.addSheet(sheetName, requestedBy)
    sheetRepository.save(sheet.id, Version.zero, sheetEvent)

    return sheet.id
  }

}