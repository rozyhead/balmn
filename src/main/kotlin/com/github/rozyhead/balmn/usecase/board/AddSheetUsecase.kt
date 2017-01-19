package com.github.rozyhead.balmn.usecase.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.service.repository.BoardRepository
import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetName
import com.github.rozyhead.balmn.service.repository.SheetRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddSheetUsecase(
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository
) {

  data class Command(
      val accountName: AccountName,
      val boardName: BoardName,
      val sheetName: SheetName,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (accountName, boardName, sheetName, requestedBy) = command

    val boardWithEvents = boardRepository.findByAccountNameAndBoardName(accountName, boardName)
        ?: throw BoardOperationException.boardNotFound(accountName, boardName)

    val (board, oldBoardEvents) = boardWithEvents
    if (!board.allowSheetAdditionByUser(requestedBy)) {
      throw BoardOperationException.sheetAdditionNotAllowed(accountName, boardName, requestedBy.accountName)
    }

    val (sheet, sheetEvent) = Sheet.create(sheetName, requestedBy.accountName)
    sheetRepository.save(sheet.id, listOf(sheetEvent), emptyList())

    val (newBoard, boardEvent) = board.addSheet(sheet.id, requestedBy.accountName)
    boardRepository.save(newBoard.accountName, newBoard.boardName, listOf(boardEvent), oldBoardEvents)
  }
}