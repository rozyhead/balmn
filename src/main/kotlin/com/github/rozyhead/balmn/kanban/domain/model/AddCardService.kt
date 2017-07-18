package com.github.rozyhead.balmn.kanban.domain.model

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.domain.model.board.Board
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwnerService
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId
import com.github.rozyhead.balmn.kanban.domain.model.card.CardRepository
import com.github.rozyhead.balmn.kanban.domain.model.card.CardTitle
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetRepository
import org.springframework.stereotype.Service

@Service
class AddCardService(
    val boardGrantService: BoardGrantService,
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository,
    val cardRepository: CardRepository
) {

  @Throws(BoardOperationException::class)
  fun addCard(sheetId: SheetId, cardTitle: CardTitle, requestedBy: UserId): CardId {
    val (sheet) = sheetRepository.find(sheetId)
        ?: throw BoardOperationException.sheetNotFound(sheetId)

    val (board) = boardRepository.find(sheet.boardId)
        ?: throw BoardOperationException.sheetNotFound(sheetId)

    // カードの追加権限チェック
    checkGrant(board, requestedBy)

    val (card, cardEvent) = sheet.addCard(cardTitle, requestedBy)
    cardRepository.save(card.id, Version.zero, cardEvent)

    return card.id
  }

  private fun checkGrant(board: Board, userId: UserId) {
    if (!boardGrantService.allowCardAdditionByUser(board, userId)) {
      throw BoardOperationException.boardOperationNotAllowed(board.owner, board.name)
    }
  }

}