package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardId
import com.github.rozyhead.balmn.service.repository.BoardRepository
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.service.repository.CardRepository
import com.github.rozyhead.balmn.domain.model.board.comment.Comment
import com.github.rozyhead.balmn.service.repository.CommentRepository
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.service.repository.SheetRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class AddCommentUsacase(
    val boardRepository: BoardRepository,
    val cardRepository: CardRepository,
    val sheetRepository: SheetRepository,
    val commentRepository: CommentRepository
) {

  data class Command(
      val boardId: BoardId,
      val sheetId: SheetId,
      val cardId: CardId,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (boardIdentifier, sheetIdentifier, cardIdentifier, requestedBy) = command

    val boardWithEvents = boardRepository.findById(boardIdentifier)
        ?: throw BoardOperationException.boardNotFound(boardIdentifier)

    val (board) = boardWithEvents
    if (!board.allowCommentAdditionByUser(requestedBy)) {
      throw BoardOperationException.commentAdditionNotAllowed(boardIdentifier, requestedBy.accountName)
    }

    val sheetWithEvents = sheetRepository.findById(sheetIdentifier)
        ?: throw BoardOperationException.sheetNotFound(boardIdentifier, sheetIdentifier)

    if (!board.hasSheet(sheetIdentifier)) {
      throw BoardOperationException.sheetNotFound(boardIdentifier, sheetIdentifier)
    }

    if (!cardRepository.exists(cardIdentifier)) {
      throw BoardOperationException.cardNotFound(boardIdentifier, sheetIdentifier, cardIdentifier)
    }

    val (sheet) = sheetWithEvents
    if (!sheet.hasCard(cardIdentifier)) {
      throw BoardOperationException.cardNotFound(boardIdentifier, sheetIdentifier, cardIdentifier)
    }

    val (comment, commentEvent) = Comment.create(cardIdentifier, requestedBy.accountName)
    commentRepository.save(comment.id, listOf(commentEvent), emptyList())
  }

}