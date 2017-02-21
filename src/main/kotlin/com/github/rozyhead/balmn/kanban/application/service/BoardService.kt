package com.github.rozyhead.balmn.kanban.application.service

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.kanban.application.exception.BoardOperationException
import com.github.rozyhead.balmn.kanban.application.index.BoardNameIndex
import com.github.rozyhead.balmn.kanban.application.repository.BoardRepository
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.board.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    val boardOwnerService: BoardOwnerService,
    val boardNameIndex: BoardNameIndex,
    val boardRepository: BoardRepository
) {

  data class CreateNewBoardCommand(
      val boardOwner: BoardOwner,
      val boardName: BoardName,
      val requestedBy: UserId
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun createNewBoard(command: CreateNewBoardCommand): BoardId {
    val (boardOwner, boardName, requestedBy) = command

    if (!boardOwnerService.exists(boardOwner)) {
      throw BoardOperationException.boardOwnerNotFound(boardOwner)
    }

    if (!boardOwnerService.isMember(boardOwner, requestedBy)) {
      throw BoardOperationException.boardOperationNotAllowed(boardOwner, boardName)
    }

    if (boardNameIndex.exists(boardOwner, boardName)) {
      throw BoardOperationException.boardAlreadyExists(boardOwner, boardName)
    }

    val (board, boardEvent) = Board.create(boardOwner, boardName, requestedBy)
    boardRepository.save(board.id, Version.zero, boardEvent)

    return board.id
  }

}

