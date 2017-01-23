package com.github.rozyhead.balmn.usecase.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.domain.model.board.comment.Comment
import com.github.rozyhead.balmn.domain.model.board.comment.CommentContent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.service.repository.BoardRepository
import com.github.rozyhead.balmn.service.repository.CardRepository
import com.github.rozyhead.balmn.service.repository.CommentRepository
import com.github.rozyhead.balmn.service.repository.SheetRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import com.github.rozyhead.balmn.util.ddd.Version
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddCommentUsacase(
    val boardRepository: BoardRepository,
    val cardRepository: CardRepository,
    val sheetRepository: SheetRepository,
    val commentRepository: CommentRepository
) {

  data class Command(
      val accountName: AccountName,
      val boardName: BoardName,
      val sheetId: SheetId,
      val cardId: CardId,
      val commentContent: CommentContent,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (accountName, boardName, sheetId, cardId, commentContent, requestedBy) = command

    val boardWithEvents = boardRepository.findByAccountNameAndBoardName(accountName, boardName)
        ?: throw BoardOperationException.boardNotFound(accountName, boardName)

    val (board) = boardWithEvents
    if (!board.allowCommentAdditionByUser(requestedBy)) {
      throw BoardOperationException.commentAdditionNotAllowed(accountName, boardName, requestedBy.accountName)
    }

    val sheetWithEvents = sheetRepository.findById(sheetId)
        ?: throw BoardOperationException.sheetNotFound(accountName, boardName, sheetId)

    if (!board.hasSheet(sheetId)) {
      throw BoardOperationException.sheetNotFound(accountName, boardName, sheetId)
    }

    if (!cardRepository.exists(cardId)) {
      throw BoardOperationException.cardNotFound(accountName, boardName, sheetId, cardId)
    }

    val (sheet) = sheetWithEvents
    if (!sheet.hasCard(cardId)) {
      throw BoardOperationException.cardNotFound(accountName, boardName, sheetId, cardId)
    }

    val (comment, commentEvent) = Comment.create(cardId, commentContent, requestedBy.accountName)
    commentRepository.save(comment.id, Version.zero, commentEvent)
  }

}