package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PlainPassword
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
  @Throws(UserRegistrationException::class)
  fun execute(command: Command)

}

sealed class UserRegistrationException(message: String) : Exception(message) {
  class AccountNameAlreadyUsedException(accountName: AccountName) : UserRegistrationException(accountName.value)
}
