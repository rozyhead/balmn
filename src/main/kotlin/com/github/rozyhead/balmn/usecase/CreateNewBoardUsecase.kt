package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import org.springframework.transaction.annotation.Transactional

interface CreateNewBoardUsecase {

  data class Command(
      val userAccount: UserAccount,
      val boardIdentifier: BoardIdentifier
  )

  @Transactional
  @Throws(BoardCreationException::class)
  fun execute(command: Command)

}

sealed class BoardCreationException(message: String) : Exception(message) {
  class OwnerAccountNotFoundException(ownerAccountName: AccountName) : BoardCreationException(ownerAccountName.value)
  class NotAuthorizedException(ownerAccountName: AccountName, creatingBy: AccountName) : BoardCreationException("${creatingBy.value} can't create board to account ${ownerAccountName.value}")
  class BoardAlreadyExistsException(boardIdentifier: BoardIdentifier) : BoardCreationException("${boardIdentifier.accountName.value}/${boardIdentifier.boardName.value}")
}