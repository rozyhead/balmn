package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.domain.model.board.card.Card
import com.github.rozyhead.balmn.domain.model.board.card.CardRepository
import com.github.rozyhead.balmn.domain.model.board.card.CardTitle
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class AddCardUsacase(
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository,
    val cardRepository: CardRepository
) {

  data class Command(
      val boardIdentifier: BoardIdentifier,
      val sheetIdentifier: SheetIdentifier,
      val cardTitle: CardTitle,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (boardIdentifier, sheetIdentifier, cardTitle, requestedBy) = command

    val boardWithEvents = boardRepository.findByIdentifier(boardIdentifier)
        ?: throw BoardOperationException.boardNotFound(boardIdentifier)

    val (board, oldBoardEvents) = boardWithEvents
    if (!board.allowCardAdditionByUser(requestedBy)) {
      throw BoardOperationException.cardAdditionNotAllowed(boardIdentifier, requestedBy.accountName)
    }

    val sheetWithEvents = sheetRepository.findByIdentifier(sheetIdentifier)
        ?: throw BoardOperationException.sheetNotFound(boardIdentifier, sheetIdentifier)

    if (!board.hasSheet(sheetIdentifier)) {
      throw BoardOperationException.sheetNotFound(boardIdentifier, sheetIdentifier)
    }

    val (card, cardEvent) = Card.create(cardTitle, requestedBy.accountName)
    cardRepository.save(card.identifier, listOf(cardEvent), emptyList())

    val (sheet, oldSheetEvents) = sheetWithEvents
    val (newSheet, sheetEvent) = sheet.addCard(card.identifier, requestedBy.accountName)
    sheetRepository.save(newSheet.identifier, listOf(sheetEvent), oldSheetEvents)
  }

}