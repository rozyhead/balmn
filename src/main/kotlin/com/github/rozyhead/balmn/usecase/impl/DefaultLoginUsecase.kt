package com.github.rozyhead.balmn.usecase.impl

import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.authentication.PasswordAuthenticationService
import com.github.rozyhead.balmn.usecase.LoginFailureException
import com.github.rozyhead.balmn.usecase.LoginUsecase
import org.springframework.stereotype.Service

@Service
open class DefaultLoginUsecase(
    val passwordAuthenticationService: PasswordAuthenticationService
) : LoginUsecase {

  override fun execute(command: LoginUsecase.Command): UserAccount {
    return passwordAuthenticationService.authenticate(
        command.accountName, command.plainPassword) ?: throw LoginFailureException()
  }

}