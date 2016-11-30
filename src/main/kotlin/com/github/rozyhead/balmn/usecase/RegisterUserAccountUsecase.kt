package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.service.repository.AccountRepository
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.service.repository.UserAccountRepository
import com.github.rozyhead.balmn.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.service.repository.PasswordAuthenticationRepository
import com.github.rozyhead.balmn.domain.model.authentication.password.PlainPassword
import com.github.rozyhead.balmn.usecase.exception.AccountOperationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class RegisterUserAccountUsecase(
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
    userAccountRepository.save(userAccount.accountName, listOf(userAccountEvent), emptyList())

    val (passwordAuthentication, passwordAuthenticationEvent) = PasswordAuthentication.create(accountName, plainPassword)
    passwordAuthenticationRepository.save(passwordAuthentication.accountName, listOf(passwordAuthenticationEvent), emptyList())
  }

}

