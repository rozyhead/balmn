package com.github.rozyhead.balmn.usecase.authentication

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PlainPassword
import com.github.rozyhead.balmn.ifadapter.persistence.repository.delegate.DelegatingAccountRepository
import com.github.rozyhead.balmn.ifadapter.persistence.repository.memory.InMemoryPasswordAuthenticationRepository
import com.github.rozyhead.balmn.ifadapter.persistence.repository.memory.InMemoryUserAccountRepository
import com.github.rozyhead.balmn.usecase.exception.AccountOperationException
import com.github.rozyhead.balmn.util.ddd.Version
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

    assertThat(userAccountRepository.existsInMemory(accountName)).isTrue()
    assertThat(passwordAuthenticationRepository.existsInMemory(accountName)).isTrue()
  }

  @Test
  fun execute_when_account_already_exists() {
    val accountName = AccountName("test")
    userAccountRepository.saveToMemory(accountName, Version.zero)

    val plainPassword = PlainPassword("secret")
    val command = RegisterUserAccountUsecase.Command(accountName, plainPassword)

    assertThatThrownBy { sut.execute(command) }
        .isInstanceOf(AccountOperationException.AccountNameAlreadyUsedException::class.java)
  }

}