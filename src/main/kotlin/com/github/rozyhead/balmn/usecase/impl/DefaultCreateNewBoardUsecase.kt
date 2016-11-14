package com.github.rozyhead.balmn.usecase.impl

import com.github.rozyhead.balmn.domain.model.account.AccountRepository
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardRepository
import com.github.rozyhead.balmn.usecase.AccountNotFoundException
import com.github.rozyhead.balmn.usecase.BoardIdentifierAlreadyExistsException
import com.github.rozyhead.balmn.usecase.CreateNewBoardUsecase
import com.github.rozyhead.balmn.usecase.NotAuthorizedException
import org.springframework.stereotype.Service

@Service
open class DefaultCreateNewBoardUsecase(
    val accountRepository: AccountRepository,
    val boardRepository: BoardRepository
) : CreateNewBoardUsecase {

  override fun execute(command: CreateNewBoardUsecase.Command) {
    val boardIdentifier = command.boardIdentifier
    val ownerAccountName = boardIdentifier.accountName

    val ownerAccount = accountRepository.findByAccountName(ownerAccountName)
        ?: throw AccountNotFoundException(ownerAccountName)

    if (!ownerAccount.creatableBoardByUser(command.userAccount.accountName)) {
      throw NotAuthorizedException()
    }

    if (boardRepository.exists(boardIdentifier)) {
      throw BoardIdentifierAlreadyExistsException(boardIdentifier)
    }

    val board = Board(boardIdentifier)
    boardRepository.save(board)
  }

}