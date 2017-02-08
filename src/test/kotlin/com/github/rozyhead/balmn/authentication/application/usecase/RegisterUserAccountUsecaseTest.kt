package com.github.rozyhead.balmn.authentication.application.usecase

import com.github.rozyhead.balmn.account.application.exception.AccountOperationException
import com.github.rozyhead.balmn.account.application.usecase.RegisterUserAccountUsecase
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.port.adapter.repository.delegate.DelegatingAccountRepository
import com.github.rozyhead.balmn.account.port.adapter.repository.memory.InMemoryUserAccountRepository
import com.github.rozyhead.balmn.authentication.domain.model.password.PlainPassword
import com.github.rozyhead.balmn.authentication.port.adapter.repository.memory.InMemoryPasswordAuthenticationRepository
import com.github.rozyhead.balmn.common.domain.model.Version
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class RegisterUserAccountUsecaseTest {

  val userAccountRepository = InMemoryUserAccountRepository()
  val accountRepository = DelegatingAccountRepository(userAccountRepository)
  val passwordAuthenticationRepository = InMemoryPasswordAuthenticationRepository()

  val sut = RegisterUserAccountUsecase(accountRepository, userAccountRepository, passwordAuthenticationRepository)

  @Test
  fun execute() {
    val accountName = AccountName("test")
    val plainPassword = PlainPassword("secret")
    val command = RegisterUserAccountUsecase.Command(accountName, plainPassword)

    sut.execute(command)

    assertThat(userAccountRepository.exists(accountName)).isTrue()
    assertThat(passwordAuthenticationRepository.exists(accountName)).isTrue()
  }

  @Test
  fun execute_when_account_already_exists() {
    val accountName = AccountName("test")
    userAccountRepository.save(accountName, Version.zero)

    val plainPassword = PlainPassword("secret")
    val command = RegisterUserAccountUsecase.Command(accountName, plainPassword)

    assertThatThrownBy { sut.execute(command) }
        .isInstanceOf(AccountOperationException.AccountNameAlreadyUsedException::class.java)
  }

}