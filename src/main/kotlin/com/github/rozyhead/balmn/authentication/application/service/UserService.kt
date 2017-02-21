package com.github.rozyhead.balmn.authentication.application.service

import com.github.rozyhead.balmn.authentication.application.exception.UserOperationException
import com.github.rozyhead.balmn.authentication.application.index.UserNameIndex
import com.github.rozyhead.balmn.authentication.application.repository.UserRepository
import com.github.rozyhead.balmn.authentication.domain.model.User
import com.github.rozyhead.balmn.authentication.domain.model.UserName
import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthentication
import com.github.rozyhead.balmn.authentication.domain.model.password.PlainPassword
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    val userNameIndex: UserNameIndex,
    val userRepository: UserRepository
) {

  data class RegisterPasswordAuthenticationUserCommand(
      val userName: UserName,
      val plainPassword: PlainPassword
  )

  @Transactional
  @Throws(UserOperationException::class)
  fun registerPasswordAuthenticationUser(command: RegisterPasswordAuthenticationUserCommand) {
    val (userName, plainPassword) = command
    if (userNameIndex.exists(userName)) {
      throw UserOperationException.userNameAlreadyUsed(userName)
    }

    val passwordAuthentication = PasswordAuthentication.fromPlainPassword(plainPassword)
    val (user, userEvent) = User.create(userName, passwordAuthentication)

    userRepository.save(user.id, Version.zero, userEvent)
    userNameIndex.save(user.name, user.id)
  }

}