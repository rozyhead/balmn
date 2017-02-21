package com.github.rozyhead.balmn.authentication.domain.model

import java.util.*

data class UserId(val uuid: UUID) {

  companion object {
    fun generate(): UserId = UserId(UUID.randomUUID())
  }

}