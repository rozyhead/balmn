package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.PlainPassword
import org.springframework.transaction.annotation.Transactional

/**
 * ユーザー登録Usecase
 */
interface RegisterUserAccountUsecase {

  data class Command(
      val accountName: AccountName,
      val plainPassword: PlainPassword
  )

  @Transactional
  @Throws(AccountNameAlreadyUsedException::class)
  fun execute(command: Command)

}

