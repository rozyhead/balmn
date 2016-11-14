package com.github.rozyhead.balmn.domain.model.authentication

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.account.UserAccountRepository
import org.springframework.stereotype.Component

/**
 * @author takeshi
 */
@Component
open class PasswordAuthenticationService(
    val passwordAuthenticationRepository: PasswordAuthenticationRepository,
    val userAccountRepository: UserAccountRepository
) {

  fun authenticate(accountName: AccountName, plainPassword: PlainPassword): UserAccount? {
    val passwordAuthentication = passwordAuthenticationRepository.findByAccountName(accountName) ?: return null
    if (passwordAuthentication.authenticate(accountName, plainPassword)) {
      return userAccountRepository.findByAccountName(accountName)
    }
    return null;
  }
}