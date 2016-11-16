package com.github.rozyhead.balmn.usecase.impl

import com.github.rozyhead.balmn.domain.model.account.AccountRepository
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.usecase.BoardCreationException.*
import com.github.rozyhead.balmn.usecase.CreateNewBoardUsecase
import org.springframework.stereotype.Service

@Service
open class DefaultCreateNewBoardUsecase(
    val accountRepository: AccountRepository,
    val boardRepository: BoardRepository
) : CreateNewBoardUsecase {

  override fun execute(command: CreateNewBoardUsecase.Command) {
    val (userAccount, boardIdentifier) = command
    val ownerAccountName = boardIdentifier.accountName

    val ownerAccount = accountRepository.findByAccountName(ownerAccountName)
        ?: throw OwnerAccountNotFoundException(ownerAccountName)

    if (!ownerAccount.allowBoardCreationForUser(userAccount.accountName)) {
      throw NotAuthorizedException(ownerAccountName, userAccount.accountName)
    }

    if (boardRepository.exists(boardIdentifier)) {
      throw BoardAlreadyExistsException(boardIdentifier)
    }

    val (board, event) = Board.create(boardIdentifier, userAccount.accountName)
    boardRepository.save(board.identifier, listOf(event), emptyList())
  }

}