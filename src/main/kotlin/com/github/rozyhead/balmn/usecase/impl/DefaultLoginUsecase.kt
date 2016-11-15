package com.github.rozyhead.balmn.usecase.impl

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.account.UserAccountRepository
import com.github.rozyhead.balmn.domain.model.authentication.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.domain.model.authentication.PlainPassword
import com.github.rozyhead.balmn.usecase.LoginFailureException
import com.github.rozyhead.balmn.usecase.LoginUsecase
import org.springframework.stereotype.Service

@Service
open class DefaultLoginUsecase(
    val passwordAuthenticationRepository: PasswordAuthenticationRepository,
    val userAccountRepository: UserAccountRepository
) : LoginUsecase {

  override fun execute(command: LoginUsecase.Command): UserAccount {
    val (accountName, plainPassword) = command;
    return authenticate(accountName, plainPassword) ?: throw LoginFailureException()
  }

  fun authenticate(accountName: AccountName, plainPassword: PlainPassword): UserAccount? {
    val passwordAuthentication = passwordAuthenticationRepository.findByAccountName(accountName) ?: return null
    if (!passwordAuthentication.authenticate(accountName, plainPassword)) {
      return null;
    }
    return userAccountRepository.findByAccountName(accountName)
  }

}