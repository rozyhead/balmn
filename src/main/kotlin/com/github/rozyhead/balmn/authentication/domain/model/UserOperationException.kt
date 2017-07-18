package com.github.rozyhead.balmn.authentication.domain.model

sealed class UserOperationException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause){

  class UserNameAlreadyUsedException(userName: UserName)
    : UserOperationException(userName.value)

  companion object {
    fun userNameAlreadyUsed(userName: UserName) = UserNameAlreadyUsedException(userName)
  }

}