package com.github.rozyhead.balmn.usecase.exception

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.BoardId
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.domain.model.board.card.CardId

sealed class BoardOperationException(message: String) : Exception(message) {

  class OwnerAccountNotFoundException(ownerAccountName: AccountName)
    : BoardOperationException(ownerAccountName.value)

  class BoardCreationNotAllowedException(ownerAccountName: AccountName, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't create board to account ${ownerAccountName.value}")

  class BoardAlreadyExistsException(boardId: BoardId)
    : BoardOperationException("$boardId")

  class BoardNotFoundException(boardId: BoardId)
    : BoardOperationException("$boardId")

  class SheetAdditionNotAllowedException(boardId: BoardId, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add sheet to board $boardId")

  class CardAdditionNotAllowedException(boardId: BoardId, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add card to board $boardId")

  class SheetNotFoundException(boardId: BoardId, sheetId: SheetId)
    : BoardOperationException("$sheetId in $boardId")

  class CommentAdditionNotAllowedException(boardId: BoardId, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add comment to board $boardId")

  class CardNotFoundException(boardId: BoardId, sheetId: SheetId, cardId: CardId)
    : BoardOperationException("$cardId in $sheetId in $boardId")

  companion object {

    fun ownerAccountNotFound(ownerAccountName: AccountName): OwnerAccountNotFoundException {
      return OwnerAccountNotFoundException(ownerAccountName)
    }

    fun boardCreationNotAllowed(ownerAccountName: AccountName, requestedBy: AccountName): BoardCreationNotAllowedException {
      return BoardCreationNotAllowedException(ownerAccountName, requestedBy)
    }

    fun boardAlreadyExists(boardId: BoardId): BoardAlreadyExistsException {
      return BoardAlreadyExistsException(boardId)
    }

    fun boardNotFound(boardId: BoardId): BoardNotFoundException {
      return BoardNotFoundException(boardId)
    }

    fun sheetAdditionNotAllowed(boardId: BoardId, requestedBy: AccountName): SheetAdditionNotAllowedException {
      return SheetAdditionNotAllowedException(boardId, requestedBy)
    }

    fun cardAdditionNotAllowed(boardId: BoardId, requestedBy: AccountName): CardAdditionNotAllowedException {
      return CardAdditionNotAllowedException(boardId, requestedBy)
    }

    fun sheetNotFound(boardId: BoardId, sheetId: SheetId): SheetNotFoundException {
      return SheetNotFoundException(boardId, sheetId)
    }

    fun commentAdditionNotAllowed(boardId: BoardId, requestedBy: AccountName): CommentAdditionNotAllowedException {
      return CommentAdditionNotAllowedException(boardId, requestedBy)
    }

    fun cardNotFound(boardId: BoardId, sheetId: SheetId, cardId: CardId): CardNotFoundException {
      return CardNotFoundException(boardId, sheetId, cardId)
    }
  }

}