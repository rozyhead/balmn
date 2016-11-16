package com.github.rozyhead.balmn.usecase.impl

import com.github.rozyhead.balmn.domain.model.account.AccountRepository
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountRepository
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.usecase.RegisterUserAccountUsecase
import com.github.rozyhead.balmn.usecase.UserRegistrationException.AccountNameAlreadyUsedException
import org.springframework.stereotype.Service

@Service
open class DefaultRegisterUserAccountUsecase(
    val accountRepository: AccountRepository,
    val userAccountRepository: UserAccountRepository,
    val passwordAuthenticationRepository: PasswordAuthenticationRepository
) : RegisterUserAccountUsecase {

  override fun execute(command: RegisterUserAccountUsecase.Command) {
    val (accountName, plainPassword) = command
    if (accountRepository.exists(accountName)) {
      throw AccountNameAlreadyUsedException(accountName)
    }

    val (userAccount, userAccountEvent) = UserAccount.create(accountName)
    userAccountRepository.save(userAccount.accountName, listOf(userAccountEvent), emptyList())

    val (passwordAuthentication, passwordAuthenticationEvent) = PasswordAuthentication.create(accountName, plainPassword)
    passwordAuthenticationRepository.save(passwordAuthentication.accountName, listOf(passwordAuthenticationEvent), emptyList())
  }

}