package com.github.rozyhead.balmn.kanban.domain.model

import com.github.rozyhead.balmn.kanban.domain.model.board.BoardName
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwner
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentId
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId

sealed class BoardOperationException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

  class BoardOwnerNotFoundException(boardOwner: BoardOwner)
    : BoardOperationException(boardOwner.value)

  class BoardOperationNotAllowedException(boardOwner: BoardOwner, boardName: BoardName?)
    : BoardOperationException("$boardOwner${if (boardName == null) "" else "/" + boardName}")

  class BoardAlreadyExistsException(boardOwner: BoardOwner, boardName: BoardName)
    : BoardOperationException("$boardOwner/$boardName")

  class BoardNotFoundException(boardOwner: BoardOwner, boardName: BoardName)
    : BoardOperationException("$boardOwner/$boardName")

  class SheetNotFoundException(sheetId: SheetId)
    : BoardOperationException(sheetId.uuid.toString())

  class CardNotFoundException(cardId: CardId)
    : BoardOperationException(cardId.uuid.toString())

  class CommentNotFoundException(commentId: CommentId)
    : BoardOperationException(commentId.uuid.toString())

  companion object {
    fun boardOwnerNotFound(boardOwner: BoardOwner) = BoardOwnerNotFoundException(boardOwner)
    fun boardOperationNotAllowed(boardOwner: BoardOwner, boardName: BoardName? = null) = BoardOperationNotAllowedException(boardOwner, boardName)
    fun boardAlreadyExists(boardOwner: BoardOwner, boardName: BoardName) = BoardAlreadyExistsException(boardOwner, boardName)
    fun boardNotFound(boardOwner: BoardOwner, boardName: BoardName) = BoardNotFoundException(boardOwner, boardName)
    fun sheetNotFound(sheetId: SheetId) = SheetNotFoundException(sheetId)
    fun cardNotFound(cardId: CardId) = CardNotFoundException(cardId)
    fun commentNotFound(commentId: CommentId) = CommentNotFoundException(commentId)
  }

}