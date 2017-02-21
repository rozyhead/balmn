package com.github.rozyhead.balmn.kanban.application.service

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.application.exception.BoardOperationException
import com.github.rozyhead.balmn.kanban.application.repository.BoardRepository
import com.github.rozyhead.balmn.kanban.application.repository.CardRepository
import com.github.rozyhead.balmn.kanban.application.repository.CommentRepository
import com.github.rozyhead.balmn.kanban.application.repository.SheetRepository
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwnerService
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentContent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    val boardOwnerService: BoardOwnerService,
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository,
    val cardRepository: CardRepository,
    val commentRepository: CommentRepository
) {

  data class AddCommentCommand(
      val cardId: CardId,
      val commentContent: CommentContent,
      val requestedBy: UserId
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun addComment(command: AddCommentCommand) {
    val (cardId, commentContent, requestedBy) = command

    val (card) = cardRepository.find(cardId)
        ?: throw BoardOperationException.cardNotFound(cardId)

    val (sheet) = sheetRepository.find(card.sheetId)
        ?: throw BoardOperationException.cardNotFound(cardId)

    val (board) = boardRepository.find(sheet.boardId)
        ?: throw BoardOperationException.cardNotFound(cardId)

    if (!board.allowCommentAdditionByUser(boardOwnerService, requestedBy)) {
      throw BoardOperationException.boardOperationNotAllowed(board.owner, board.name)
    }

    val (comment, commentEvent) = card.addComment(commentContent, requestedBy)
    commentRepository.save(comment.id, Version.zero, commentEvent)
  }

}