package com.github.rozyhead.balmn.authentication.application.service

import com.github.rozyhead.balmn.authentication.application.exception.UserOperationException
import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.authentication.domain.model.UserName
import com.github.rozyhead.balmn.authentication.domain.model.password.PlainPassword
import com.github.rozyhead.balmn.authentication.port.adapter.index.memory.InMemoryUserNameIndex
import com.github.rozyhead.balmn.authentication.port.adapter.repository.memory.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class UserServiceTest {

  val userNameIndex = InMemoryUserNameIndex()
  val userRepository = InMemoryUserRepository()

  val sut = UserService(userNameIndex, userRepository)

  @Test
  fun registerPasswordAuthenticationUser() {
    val userName = UserName("test")
    val plainPassword = PlainPassword("secret")
    val command = UserService.RegisterPasswordAuthenticationUserCommand(userName, plainPassword)

    val userId = sut.registerPasswordAuthenticationUser(command)
    assertThat(userRepository.exists(userId)).isTrue()

    val (user) = userRepository.find(userId)!!
    assertThat(user.name).isEqualTo(userName)
  }

  @Test
  fun registerPasswordAuthenticationUser_when_account_already_exists() {
    val userName = UserName("test")
    userNameIndex.save(userName, UserId.generate())

    val plainPassword = PlainPassword("secret")
    val command = UserService.RegisterPasswordAuthenticationUserCommand(userName, plainPassword)

    assertThatThrownBy { sut.registerPasswordAuthenticationUser(command) }
        .isInstanceOf(UserOperationException.UserNameAlreadyUsedException::class.java)
  }

}