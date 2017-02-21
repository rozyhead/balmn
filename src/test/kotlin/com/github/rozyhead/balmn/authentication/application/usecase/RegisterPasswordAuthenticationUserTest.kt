package com.github.rozyhead.balmn.authentication.application.usecase

import com.github.rozyhead.balmn.account.application.exception.AccountOperationException
import com.github.rozyhead.balmn.account.application.usecase.RegisterUserAccountUsecase
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.authentication.application.exception.UserOperationException
import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.authentication.domain.model.UserName
import com.github.rozyhead.balmn.authentication.domain.model.password.PlainPassword
import com.github.rozyhead.balmn.authentication.port.adapter.index.memory.InMemoryUserNameIndex
import com.github.rozyhead.balmn.authentication.port.adapter.repository.memory.InMemoryUserRepository
import com.github.rozyhead.balmn.common.domain.model.Version
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class RegisterPasswordAuthenticationUserTest {

  val userNameIndex = InMemoryUserNameIndex()
  val userRepository = InMemoryUserRepository()

  val sut = RegisterPasswordAuthenticationUser(userNameIndex, userRepository)

  @Test
  fun execute() {
    val userName = UserName("test")
    val plainPassword = PlainPassword("secret")
    val command = RegisterPasswordAuthenticationUser.Command(userName, plainPassword)

    sut.execute(command)

    assertThat(userNameIndex.exists(userName)).isTrue()

    val userId = userNameIndex.find(userName)!!
    assertThat(userRepository.exists(userId)).isTrue()

    val (user) = userRepository.find(userId)!!
    assertThat(user.name).isEqualTo(userName)
  }

  @Test
  fun execute_when_account_already_exists() {
    val userName = UserName("test")
    userNameIndex.save(userName, UserId.generate())

    val plainPassword = PlainPassword("secret")
    val command = RegisterPasswordAuthenticationUser.Command(userName, plainPassword)

    assertThatThrownBy { sut.execute(command) }
        .isInstanceOf(UserOperationException.UserNameAlreadyUsedException::class.java)
  }

}