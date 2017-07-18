package com.github.rozyhead.balmn.authentication.domain.model

import com.github.rozyhead.balmn.authentication.domain.model.password.PlainPassword
import com.github.rozyhead.balmn.authentication.port.adapter.index.memory.InMemoryUserNameIndex
import com.github.rozyhead.balmn.authentication.port.adapter.repository.memory.InMemoryUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class RegisterUserByPasswordAuthenticationServiceTest {

  val userNameIndex = InMemoryUserNameIndex()
  val userRepository = InMemoryUserRepository()

  val sut = RegisterUserByPasswordAuthenticationService(userNameIndex, userRepository)

  @Test
  fun registerPasswordAuthenticationUser() {
    val userName = UserName("test")
    val plainPassword = PlainPassword("secret")

    val userId = sut.registerPasswordAuthenticationUser(userName, plainPassword)

    val (user) = userRepository.find(userId)!!
    assertThat(user.name).isEqualTo(userName)
    assertThat(userNameIndex.find(userName)).isEqualTo(userId)
  }

  @Test
  fun registerPasswordAuthenticationUser_when_account_already_exists() {
    val userName = UserName("test")
    userNameIndex.save(userName, UserId.generate())

    val plainPassword = PlainPassword("secret")

    assertThatThrownBy { sut.registerPasswordAuthenticationUser(userName, plainPassword) }
        .isInstanceOf(UserOperationException.UserNameAlreadyUsedException::class.java)
  }

}