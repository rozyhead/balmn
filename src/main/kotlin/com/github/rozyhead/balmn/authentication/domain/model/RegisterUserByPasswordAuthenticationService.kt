package com.github.rozyhead.balmn.authentication.domain.model

import com.github.rozyhead.balmn.authentication.domain.model.password.PasswordAuthentication
import com.github.rozyhead.balmn.authentication.domain.model.password.PlainPassword
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service

@Service
class RegisterUserByPasswordAuthenticationService(
    val userNameIndex: UserNameIndex,
    val userRepository: UserRepository
) {

  @Throws(UserOperationException::class)
  fun registerPasswordAuthenticationUser(userName: UserName, plainPassword: PlainPassword): UserId {

    // 同一ユーザー名の存在チェック
    checkUserNameNotUsed(userName)

    // ユーザーを作成して保存
    val passwordAuthentication = PasswordAuthentication.fromPlainPassword(plainPassword)
    val (user, userEvent) = User.create(userName, passwordAuthentication)
    userRepository.save(user.id, Version.zero, userEvent)

    // ユーザー名インデックスに保存
    userNameIndex.save(userName, user.id)

    return user.id
  }

  private fun checkUserNameNotUsed(userName: UserName) {
    if (userNameIndex.exists(userName)) {
      throw UserOperationException.userNameAlreadyUsed(userName)
    }
  }

}