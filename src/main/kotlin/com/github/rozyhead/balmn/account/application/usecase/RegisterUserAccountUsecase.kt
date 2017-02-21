package com.github.rozyhead.balmn.account.application.usecase

import com.github.rozyhead.balmn.account.application.exception.AccountOperationException
import com.github.rozyhead.balmn.account.application.index.AccountNameIndex
import com.github.rozyhead.balmn.account.application.repository.UserAccountRepository
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.AccountType
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterUserAccountUsecase(
    val accountNameIndex: AccountNameIndex,
    val userAccountRepository: UserAccountRepository
) {

  data class Command(
      val accountName: AccountName
  )

  @Transactional
  @Throws(AccountOperationException::class)
  fun execute(command: Command) {
    val (accountName) = command
    if (accountNameIndex.exists(accountName)) {
      throw AccountOperationException.accountNameAlreadyUsed(accountName)
    }

    val (userAccount, userAccountEvent) = UserAccount.create(accountName)
    userAccountRepository.save(userAccount.id, Version.zero, userAccountEvent)
    accountNameIndex.save(userAccount.accountName, userAccount.id, AccountType.UserAccount)
  }

}

