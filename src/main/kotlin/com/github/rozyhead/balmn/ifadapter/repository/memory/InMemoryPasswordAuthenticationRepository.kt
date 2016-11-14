package com.github.rozyhead.balmn.ifadapter.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.authentication.PasswordAuthentication
import com.github.rozyhead.balmn.domain.model.authentication.PasswordAuthenticationRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryPasswordAuthenticationRepository : PasswordAuthenticationRepository {

  val entities = mutableMapOf<AccountName, PasswordAuthentication>()

  override fun findByAccountName(accountName: AccountName): PasswordAuthentication? {
    return entities[accountName]
  }

  override fun save(passwordAuthentication: PasswordAuthentication) {
    entities[passwordAuthentication.accountName] = passwordAuthentication
  }

}