package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.board.BoardIdentifier
import org.springframework.transaction.annotation.Transactional

interface CreateNewBoardUsecase {

  data class Command(
      val userAccount: UserAccount,
      val boardIdentifier: BoardIdentifier
  )

  @Transactional
  @Throws(
      AccountNotFoundException::class,
      NotAuthorizedException::class,
      BoardIdentifierAlreadyExistsException::class
  )
  fun execute(command: Command)

}