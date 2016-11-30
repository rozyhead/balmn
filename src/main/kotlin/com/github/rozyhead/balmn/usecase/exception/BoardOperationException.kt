package com.github.rozyhead.balmn.usecase.exception

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier
import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier

sealed class BoardOperationException(message: String) : Exception(message) {

  class OwnerAccountNotFoundException(ownerAccountName: AccountName)
    : BoardOperationException(ownerAccountName.value)

  class BoardCreationNotAllowedException(ownerAccountName: AccountName, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't create board to account ${ownerAccountName.value}")

  class BoardAlreadyExistsException(boardIdentifier: BoardIdentifier)
    : BoardOperationException("$boardIdentifier")

  class BoardNotFoundException(boardIdentifier: BoardIdentifier)
    : BoardOperationException("$boardIdentifier")

  class SheetAdditionNotAllowedException(boardIdentifier: BoardIdentifier, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add sheet to board $boardIdentifier")

  class CardAdditionNotAllowedException(boardIdentifier: BoardIdentifier, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add card to board $boardIdentifier")

  class SheetNotFoundException(boardIdentifier: BoardIdentifier, sheetIdentifier: SheetIdentifier)
    : BoardOperationException("$sheetIdentifier in $boardIdentifier")

  class CommentAdditionNotAllowedException(boardIdentifier: BoardIdentifier, requestedBy: AccountName)
    : BoardOperationException("${requestedBy.value} can't add comment to board $boardIdentifier")

  class CardNotFoundException(boardIdentifier: BoardIdentifier, sheetIdentifier: SheetIdentifier, cardIdentifier: CardIdentifier)
    : BoardOperationException("$cardIdentifier in $sheetIdentifier in $boardIdentifier")

  companion object {

    fun ownerAccountNotFound(ownerAccountName: AccountName): OwnerAccountNotFoundException {
      return OwnerAccountNotFoundException(ownerAccountName)
    }

    fun boardCreationNotAllowed(ownerAccountName: AccountName, requestedBy: AccountName): BoardCreationNotAllowedException {
      return BoardCreationNotAllowedException(ownerAccountName, requestedBy)
    }

    fun boardAlreadyExists(boardIdentifier: BoardIdentifier): BoardAlreadyExistsException {
      return BoardAlreadyExistsException(boardIdentifier)
    }

    fun boardNotFound(boardIdentifier: BoardIdentifier): BoardNotFoundException {
      return BoardNotFoundException(boardIdentifier)
    }

    fun sheetAdditionNotAllowed(boardIdentifier: BoardIdentifier, requestedBy: AccountName): SheetAdditionNotAllowedException {
      return SheetAdditionNotAllowedException(boardIdentifier, requestedBy)
    }

    fun cardAdditionNotAllowed(boardIdentifier: BoardIdentifier, requestedBy: AccountName): CardAdditionNotAllowedException {
      return CardAdditionNotAllowedException(boardIdentifier, requestedBy)
    }

    fun sheetNotFound(boardIdentifier: BoardIdentifier, sheetIdentifier: SheetIdentifier): SheetNotFoundException {
      return SheetNotFoundException(boardIdentifier, sheetIdentifier)
    }

    fun commentAdditionNotAllowed(boardIdentifier: BoardIdentifier, requestedBy: AccountName): CommentAdditionNotAllowedException {
      return CommentAdditionNotAllowedException(boardIdentifier, requestedBy)
    }

    fun cardNotFound(boardIdentifier: BoardIdentifier, sheetIdentifier: SheetIdentifier, cardIdentifier: CardIdentifier): CardNotFoundException {
      return CardNotFoundException(boardIdentifier, sheetIdentifier, cardIdentifier)
    }
  }

}