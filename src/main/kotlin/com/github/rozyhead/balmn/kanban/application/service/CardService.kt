package com.github.rozyhead.balmn.kanban.application.service

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.application.exception.BoardOperationException
import com.github.rozyhead.balmn.kanban.application.repository.BoardRepository
import com.github.rozyhead.balmn.kanban.application.repository.CardRepository
import com.github.rozyhead.balmn.kanban.application.repository.SheetRepository
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwnerService
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId
import com.github.rozyhead.balmn.kanban.domain.model.card.CardTitle
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardService(
    val boardOwnerService: BoardOwnerService,
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository,
    val cardRepository: CardRepository
) {

  data class AddCardCommand(
      val sheetId: SheetId,
      val cardTitle: CardTitle,
      val requestedBy: UserId
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun addCard(command: AddCardCommand): CardId {
    val (sheetId, cardTitle, requestedBy) = command

    val (sheet) = sheetRepository.find(sheetId)
        ?: throw BoardOperationException.sheetNotFound(sheetId)

    val (board) = boardRepository.find(sheet.boardId)
        ?: throw BoardOperationException.sheetNotFound(sheetId)

    if (!board.allowCardAdditionByUser(boardOwnerService, requestedBy)) {
      throw BoardOperationException.boardOperationNotAllowed(board.owner, board.name)
    }

    val (card, cardEvent) = sheet.addCard(cardTitle, requestedBy)
    cardRepository.save(card.id, Version.zero, cardEvent)

    return card.id
  }

}