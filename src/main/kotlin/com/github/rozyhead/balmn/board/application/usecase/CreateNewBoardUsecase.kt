package com.github.rozyhead.balmn.board.application.usecase

import com.github.rozyhead.balmn.account.application.repository.AccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.board.application.exception.BoardOperationException
import com.github.rozyhead.balmn.board.application.repository.BoardRepository
import com.github.rozyhead.balmn.board.domain.model.Board
import com.github.rozyhead.balmn.board.domain.model.BoardName
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateNewBoardUsecase(
    val accountRepository: AccountRepository,
    val boardRepository: BoardRepository
) {

  data class Command(
      val accountName: AccountName,
      val boardName: BoardName,
      val requestedBy: UserAccount
  )

  @Transactional
  @Throws(BoardOperationException::class)
  fun execute(command: Command) {
    val (accountName, boardName, requestedBy) = command

    val ownerAccount = accountRepository.findByAccountName(accountName)
        ?: throw BoardOperationException.ownerAccountNotFound(accountName)

    if (!ownerAccount.allowBoardCreationForUser(requestedBy.accountName)) {
      throw BoardOperationException.boardCreationNotAllowed(accountName, requestedBy.accountName)
    }

    if (boardRepository.exists(accountName, boardName)) {
      throw BoardOperationException.boardAlreadyExists(accountName, boardName)
    }

    val (board, event) = Board.create(accountName, boardName, requestedBy.accountName)
    boardRepository.save(board.accountName, board.boardName, Version.zero, event)
  }

}

