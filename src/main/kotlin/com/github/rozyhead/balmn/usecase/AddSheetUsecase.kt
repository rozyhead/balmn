package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetName
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class AddSheetUsecase(
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository
) {

  data class Command(
      val boardIdentifier: BoardIdentifier,
      val sheetName: SheetName,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (boardIdentifier, sheetName, requestedBy) = command;

    val boardWithEvents = boardRepository.findByIdentifier(boardIdentifier)
        ?: throw BoardOperationException.boardNotFound(boardIdentifier)

    val (board, oldBoardEvents) = boardWithEvents
    if (!board.allowSheetAdditionForUser(requestedBy)) {
      throw BoardOperationException.sheetAdditionNotAllowed(boardIdentifier, requestedBy.accountName)
    }

    val (sheet, sheetEvent) = Sheet.create(sheetName, requestedBy.accountName)
    sheetRepository.save(sheet.identifier, listOf(sheetEvent), emptyList())

    val (newBoard, boardEvent) = board.addSheet(sheet.identifier, requestedBy.accountName)
    boardRepository.save(newBoard.identifier, listOf(boardEvent), oldBoardEvents)
  }
}