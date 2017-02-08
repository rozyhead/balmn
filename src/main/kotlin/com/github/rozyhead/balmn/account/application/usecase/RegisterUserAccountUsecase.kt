package com.github.rozyhead.balmn.account.application.usecase

import com.github.rozyhead.balmn.account.application.exception.AccountOperationException
import com.github.rozyhead.balmn.account.application.repository.AccountRepository
import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.authentication.application.repository.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthentication
import com.github.rozyhead.balmn.authentication.domain.model.password.PlainPassword
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterUserAccountUsecase(
    val accountRepository: AccountRepository,
    val userAccountRepository: UserAccountRepository,
    val passwordAuthenticationRepository: PasswordAuthenticationRepository
) {

  data class Command(
      val accountName: AccountName,
      val plainPassword: PlainPassword
  )

  @Transactional
  @Throws(AccountOperationException::class)
  fun execute(command: Command) {
    val (accountName, plainPassword) = command
    if (accountRepository.exists(accountName)) {
      throw AccountOperationException.accountNameAlreadyUsed(accountName)
    }

    val (userAccount, userAccountEvent) = UserAccount.create(accountName)
    userAccountRepository.save(userAccount.accountName, Version.zero, userAccountEvent)

    val (passwordAuthentication, passwordAuthenticationEvent) = PasswordAuthentication.create(accountName, plainPassword)
    passwordAuthenticationRepository.save(passwordAuthentication.accountName, Version.zero, passwordAuthenticationEvent)
  }

}

