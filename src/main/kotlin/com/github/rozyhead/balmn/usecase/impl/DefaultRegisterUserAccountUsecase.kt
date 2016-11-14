package com.github.rozyhead.balmn.usecase.impl

import com.github.rozyhead.balmn.domain.model.account.AccountRepository
import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.account.UserAccountRepository
import com.github.rozyhead.balmn.domain.model.authentication.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.usecase.AccountNameAlreadyUsedException
import com.github.rozyhead.balmn.usecase.RegisterUserAccountUsecase
import org.springframework.stereotype.Service

@Service
open class DefaultRegisterUserAccountUsecase(
    val accountRepository: AccountRepository,
    val userAccountRepository: UserAccountRepository,
    val passwordAuthenticationRepository: PasswordAuthenticationRepository
) : RegisterUserAccountUsecase {

  override fun execute(command: RegisterUserAccountUsecase.Command) {
    val accountName = command.accountName
    if (accountRepository.exists(accountName)) {
      throw AccountNameAlreadyUsedException(accountName)
    }

    val userAccount = UserAccount(accountName)
    userAccountRepository.save(userAccount)

    val encodedPassword = command.plainPassword.encode()
    val passwordAuthentication = PasswordAuthentication(accountName, encodedPassword)
    passwordAuthenticationRepository.save(passwordAuthentication)
  }

}