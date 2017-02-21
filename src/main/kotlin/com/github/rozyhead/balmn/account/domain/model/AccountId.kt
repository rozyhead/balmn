package com.github.rozyhead.balmn.account.domain.model

import java.util.*

data class AccountId(val uuid: UUID) {

  companion object {

    fun generate(): AccountId = AccountId(UUID.randomUUID())
  }

}