package com.github.rozyhead.balmn.usecase.board

import com.github.rozyhead.balmn.service.repository.AccountRepository
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardId
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.service.repository.BoardRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateNewBoardUsecase(
    val accountRepository: AccountRepository,
    val boardRepository: BoardRepository
) {

  data class Command(
      val boardId: BoardId,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (boardIdentifier, requestedBy) = command
    val ownerAccountName = boardIdentifier.accountName

    val ownerAccount = accountRepository.findByAccountName(ownerAccountName)
        ?: throw BoardOperationException.ownerAccountNotFound(ownerAccountName)

    if (!ownerAccount.allowBoardCreationForUser(requestedBy.accountName)) {
      throw BoardOperationException.boardCreationNotAllowed(ownerAccountName, requestedBy.accountName)
    }

    if (boardRepository.exists(boardIdentifier)) {
      throw BoardOperationException.boardAlreadyExists(boardIdentifier)
    }

    val (board, event) = Board.create(boardIdentifier, requestedBy.accountName)
    boardRepository.save(board.id, listOf(event), emptyList())
  }

}

