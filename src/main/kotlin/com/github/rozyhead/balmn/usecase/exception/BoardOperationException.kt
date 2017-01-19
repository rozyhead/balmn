package com.github.rozyhead.balmn.usecase.exception

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.domain.model.board.card.CardId

sealed class BoardOperationException(message: String) : Exception(message) {

  class OwnerAccountNotFoundException(accountName: AccountName)
    : BoardOperationException(accountName.value)

  class BoardCreationNotAllowedException(accountName: AccountName, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't create board to account ${accountName.value}")

  class BoardAlreadyExistsException(accountName: AccountName, boardName: BoardName)
    : BoardOperationException("$accountName/$boardName")

  class BoardNotFoundException(accountName: AccountName, boardName: BoardName)
    : BoardOperationException("$accountName/$boardName")

  class SheetAdditionNotAllowedException(accountName: AccountName, boardName: BoardName, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add sheet to board $accountName/$boardName")

  class CardAdditionNotAllowedException(accountName: AccountName, boardName: BoardName, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add card to board $accountName/$boardName")

  class SheetNotFoundException(accountName: AccountName, boardName: BoardName, sheetId: SheetId)
    : BoardOperationException("$sheetId in $accountName/$boardName")

  class CommentAdditionNotAllowedException(accountName: AccountName, boardName: BoardName, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add comment to board $accountName/$boardName")

  class CardNotFoundException(accountName: AccountName, boardName: BoardName, sheetId: SheetId, cardId: CardId)
    : BoardOperationException("$cardId in $sheetId in $accountName/$boardName")

  companion object {

    fun ownerAccountNotFound(accountName: AccountName): OwnerAccountNotFoundException {
      return OwnerAccountNotFoundException(accountName)
    }

    fun boardCreationNotAllowed(accountName: AccountName, requestedBy: AccountName): BoardCreationNotAllowedException {
      return BoardCreationNotAllowedException(accountName, requestedBy)
    }

    fun boardAlreadyExists(accountName: AccountName, boardName: BoardName): BoardAlreadyExistsException {
      return BoardAlreadyExistsException(accountName, boardName)
    }

    fun boardNotFound(accountName: AccountName, boardName: BoardName): BoardNotFoundException {
      return BoardNotFoundException(accountName, boardName)
    }

    fun sheetAdditionNotAllowed(accountName: AccountName, boardName: BoardName, requestedBy: AccountName): SheetAdditionNotAllowedException {
      return SheetAdditionNotAllowedException(accountName, boardName, requestedBy)
    }

    fun cardAdditionNotAllowed(accountName: AccountName, boardName: BoardName, requestedBy: AccountName): CardAdditionNotAllowedException {
      return CardAdditionNotAllowedException(accountName, boardName, requestedBy)
    }

    fun sheetNotFound(accountName: AccountName, boardName: BoardName, sheetId: SheetId): SheetNotFoundException {
      return SheetNotFoundException(accountName, boardName, sheetId)
    }

    fun commentAdditionNotAllowed(accountName: AccountName, boardName: BoardName, requestedBy: AccountName): CommentAdditionNotAllowedException {
      return CommentAdditionNotAllowedException(accountName, boardName, requestedBy)
    }

    fun cardNotFound(accountName: AccountName, boardName: BoardName, sheetId: SheetId, cardId: CardId): CardNotFoundException {
      return CardNotFoundException(accountName, boardName, sheetId, cardId)
    }
  }

}