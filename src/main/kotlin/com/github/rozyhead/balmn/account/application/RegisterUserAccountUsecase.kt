package com.github.rozyhead.balmn.account.application

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.authentication.password.PlainPassword
import com.github.rozyhead.balmn.account.domain.model.user.RegisterUserAccountService
import org.springframework.stereotype.Service

@Service
class RegisterUserAccountUsecase(
    private val registerUserAccountService: RegisterUserAccountService
) {

  data class RegisterUserAccountCommand(
      val accountName: AccountName,
      val plainPassword: PlainPassword
  )

  fun execute(command: RegisterUserAccountCommand) {
    val (accountName, plainPassword) = command

    registerUserAccountService.registerByPassword(accountName, plainPassword)
  }

}