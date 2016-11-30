package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.domain.model.board.card.Card
import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier
import com.github.rozyhead.balmn.domain.model.board.card.CardRepository
import com.github.rozyhead.balmn.domain.model.board.card.CardTitle
import com.github.rozyhead.balmn.domain.model.board.comment.Comment
import com.github.rozyhead.balmn.domain.model.board.comment.CommentRepository
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import com.sun.deploy.net.proxy.RemoveCommentReader
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
      val boardIdentifier: BoardIdentifier,
      val sheetIdentifier: SheetIdentifier,
      val cardIdentifier: CardIdentifier,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (boardIdentifier, sheetIdentifier, cardIdentifier, requestedBy) = command

    val boardWithEvents = boardRepository.findByIdentifier(boardIdentifier)
        ?: throw BoardOperationException.boardNotFound(boardIdentifier)

    val (board) = boardWithEvents
    if (!board.allowCommentAdditionByUser(requestedBy)) {
      throw BoardOperationException.commentAdditionNotAllowed(boardIdentifier, requestedBy.accountName)
    }

    val sheetWithEvents = sheetRepository.findByIdentifier(sheetIdentifier)
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
    commentRepository.save(comment.identifier, listOf(commentEvent), emptyList())
  }

}