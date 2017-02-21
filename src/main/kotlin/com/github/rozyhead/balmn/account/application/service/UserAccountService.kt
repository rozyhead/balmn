package com.github.rozyhead.balmn.account.application.service

import com.github.rozyhead.balmn.account.application.exception.AccountOperationException
import com.github.rozyhead.balmn.account.application.index.AccountNameIndex
import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserAccountService(
    val accountNameIndex: AccountNameIndex,
    val userAccountRepository: UserAccountRepository
) {

  data class RegisterUserAccountCommand(
      val accountName: AccountName
  )

  @Transactional
  @Throws(AccountOperationException::class)
  fun registerUserAccount(command: RegisterUserAccountCommand): AccountId {
    val (accountName) = command
    if (accountNameIndex.exists(accountName)) {
      throw AccountOperationException.accountNameAlreadyUsed(accountName)
    }

    val (userAccount, userAccountEvent) = UserAccount.create(accountName)
    userAccountRepository.save(userAccount.id, Version.zero, userAccountEvent)

    return userAccount.id
  }
}