package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.AccountRepository
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class CreateNewBoardUsecase(
    val accountRepository: AccountRepository,
    val boardRepository: BoardRepository
) {

  data class Command(
      val boardIdentifier: BoardIdentifier,
      val boardName: BoardName,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (boardIdentifier, boardName, requestedBy) = command
    val ownerAccountName = boardIdentifier.accountName

    val ownerAccount = accountRepository.findByAccountName(ownerAccountName)
        ?: throw BoardOperationException.ownerAccountNotFound(ownerAccountName)

    if (!ownerAccount.allowBoardCreationForUser(requestedBy.accountName)) {
      throw BoardOperationException.boardCreationNotAllowed(ownerAccountName, requestedBy.accountName)
    }

    if (boardRepository.exists(boardIdentifier)) {
      throw BoardOperationException.boardAlreadyExists(boardIdentifier)
    }

    val (board, event) = Board.create(boardIdentifier, boardName, requestedBy.accountName)
    boardRepository.save(board.identifier, listOf(event), emptyList())
  }

}

