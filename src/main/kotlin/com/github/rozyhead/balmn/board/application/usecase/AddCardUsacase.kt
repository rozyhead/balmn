package com.github.rozyhead.balmn.board.application.usecase

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.board.application.exception.BoardOperationException
import com.github.rozyhead.balmn.board.application.repository.BoardRepository
import com.github.rozyhead.balmn.board.application.repository.CardRepository
import com.github.rozyhead.balmn.board.application.repository.SheetRepository
import com.github.rozyhead.balmn.board.domain.model.BoardName
import com.github.rozyhead.balmn.board.domain.model.card.Card
import com.github.rozyhead.balmn.board.domain.model.card.CardTitle
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddCardUsacase(
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository,
    val cardRepository: CardRepository
) {

  data class Command(
      val accountName: AccountName,
      val boardName: BoardName,
      val sheetId: SheetId,
      val cardTitle: CardTitle,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (accountName, boardName, sheetId, cardTitle, requestedBy) = command

    val boardWithEvents = boardRepository.findByAccountNameAndBoardName(accountName, boardName)
        ?: throw BoardOperationException.boardNotFound(accountName, boardName)

    val (board) = boardWithEvents
    if (!board.allowCardAdditionByUser(requestedBy)) {
      throw BoardOperationException.cardAdditionNotAllowed(accountName, boardName, requestedBy.accountName)
    }

    val sheetWithEvents = sheetRepository.findById(sheetId)
        ?: throw BoardOperationException.sheetNotFound(accountName, boardName, sheetId)

    if (!board.hasSheet(sheetId)) {
      throw BoardOperationException.sheetNotFound(accountName, boardName, sheetId)
    }

    val (card, cardEvent) = Card.create(cardTitle, requestedBy.accountName)
    cardRepository.save(card.id, Version.zero, cardEvent)

    val (sheet, sheetVersion) = sheetWithEvents
    val (newSheet, sheetEvent) = sheet.addCard(card.id, requestedBy.accountName)
    sheetRepository.save(newSheet.id, sheetVersion, sheetEvent)
  }

}