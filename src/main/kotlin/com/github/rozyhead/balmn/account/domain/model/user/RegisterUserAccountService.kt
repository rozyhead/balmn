package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.*
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service

@Service
class RegisterUserAccountService(
    val accountNameIndex: AccountNameIndex,
    val userAccountRepository: UserAccountRepository
) {

  @Throws(AccountOperationException::class)
  fun registerUserAccount(accountName: AccountName): AccountId {

    // アカウント名の重複チェック
    checkAccountNameNotUsed(accountName)

    // 作成したアカウントを保存
    val (userAccount, userAccountEvent) = UserAccount.create(accountName)
    userAccountRepository.save(userAccount.id, Version.zero, userAccountEvent)

    // アカウント名インデックスに保存
    accountNameIndex.save(accountName, userAccount.id, AccountType.UserAccount)

    return userAccount.id
  }

  private fun checkAccountNameNotUsed(accountName: AccountName) {
    if (accountNameIndex.exists(accountName)) {
      throw AccountOperationException.accountNameAlreadyUsed(accountName)
    }
  }
}