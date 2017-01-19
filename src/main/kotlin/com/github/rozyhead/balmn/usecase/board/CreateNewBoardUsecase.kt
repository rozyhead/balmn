package com.github.rozyhead.balmn.usecase.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.service.repository.AccountRepository
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.service.repository.BoardRepository
import com.github.rozyhead.balmn.usecase.exception.BoardOperationException
import com.github.rozyhead.balmn.util.ddd.Version
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

