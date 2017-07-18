package com.github.rozyhead.balmn.kanban.domain.model

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.domain.model.board.Board
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwnerService
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId
import com.github.rozyhead.balmn.kanban.domain.model.card.CardRepository
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentContent
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentId
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentRepository
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetRepository
import org.springframework.stereotype.Service

@Service
class AddCommentService(
    val boardGrantService: BoardGrantService,
    val boardRepository: BoardRepository,
    val sheetRepository: SheetRepository,
    val cardRepository: CardRepository,
    val commentRepository: CommentRepository
) {

  @Throws(BoardOperationException::class)
  fun addComment(cardId: CardId, commentContent: CommentContent, requestedBy: UserId): CommentId {
    val (card) = cardRepository.find(cardId)
        ?: throw BoardOperationException.cardNotFound(cardId)

    val (sheet) = sheetRepository.find(card.sheetId)
        ?: throw BoardOperationException.cardNotFound(cardId)

    val (board) = boardRepository.find(sheet.boardId)
        ?: throw BoardOperationException.cardNotFound(cardId)

    // コメントの追加権限チェック
    checkGrant(board, requestedBy)

    val (comment, commentEvent) = card.addComment(commentContent, requestedBy)
    commentRepository.save(comment.id, Version.zero, commentEvent)

    return comment.id
  }

  private fun checkGrant(board: Board, userId: UserId) {
    if (!boardGrantService.allowCommentAdditionByUser(board, userId)) {
      throw BoardOperationException.boardOperationNotAllowed(board.owner, board.name)
    }
  }

}