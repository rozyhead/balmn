package com.github.rozyhead.balmn.board.application.usecase

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.board.application.exception.BoardOperationException
import com.github.rozyhead.balmn.board.application.repository.BoardRepository
import com.github.rozyhead.balmn.board.application.repository.SheetRepository
import com.github.rozyhead.balmn.board.domain.model.BoardName
import com.github.rozyhead.balmn.board.domain.model.sheet.Sheet
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetName
import com.github.rozyhead.balmn.common.domain.model.Version
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

    val (board, boardVersion) = boardWithEvents
    if (!board.allowSheetAdditionByUser(requestedBy)) {
      throw BoardOperationException.sheetAdditionNotAllowed(accountName, boardName, requestedBy.accountName)
    }

    val (sheet, sheetEvent) = Sheet.create(sheetName, requestedBy.accountName)
    sheetRepository.save(sheet.id, Version.zero, sheetEvent)

    val (newBoard, boardEvent) = board.addSheet(sheet.id, requestedBy.accountName)
    boardRepository.save(newBoard.accountName, newBoard.boardName, boardVersion, boardEvent)
  }
}