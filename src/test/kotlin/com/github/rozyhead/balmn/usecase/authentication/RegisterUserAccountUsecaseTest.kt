package com.github.rozyhead.balmn.usecase.authentication

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.password.PlainPassword
import com.github.rozyhead.balmn.ifadapter.persistence.repository.delegate.DelegatingAccountRepository
import com.github.rozyhead.balmn.ifadapter.persistence.repository.memory.InMemoryPasswordAuthenticationRepository
import com.github.rozyhead.balmn.ifadapter.persistence.repository.memory.InMemoryUserAccountRepository
import com.github.rozyhead.balmn.usecase.exception.AccountOperationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RegisterUserAccountUsecaseTest {

  val userAccountRepository = InMemoryUserAccountRepository()
  val accountRepository = DelegatingAccountRepository(userAccountRepository)
  val passwordAuthenticationRepository = InMemoryPasswordAuthenticationRepository()

  val sut = RegisterUserAccountUsecase(accountRepository, userAccountRepository, passwordAuthenticationRepository)

  @Test
  fun save_user_account_and_password_authentication_when_account_not_exists() {
    val accountName = AccountName("test")
    val plainPassword = PlainPassword("secret")
    val command = RegisterUserAccountUsecase.Command(accountName, plainPassword)

    sut.execute(command)

    assertThat(userAccountRepository.existsInMemory(accountName)).isTrue()
    assertThat(passwordAuthenticationRepository.existsInMemory(accountName)).isTrue()
  }

  @Test(expected = AccountOperationException.AccountNameAlreadyUsedException::class)
  fun throw_account_name_already_used_when_account_exists() {
    val accountName = AccountName("test")
    val plainPassword = PlainPassword("secret")
    val command = RegisterUserAccountUsecase.Command(accountName, plainPassword)
    userAccountRepository.saveToMemory(accountName, emptyList(), emptyList())

    sut.execute(command)
  }

}