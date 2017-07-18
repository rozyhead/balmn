package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.*
import com.github.rozyhead.balmn.account.domain.model.authentication.password.PasswordAuthentication
import com.github.rozyhead.balmn.account.domain.model.authentication.password.PlainPassword
import com.github.rozyhead.balmn.common.domain.model.Version
import org.springframework.stereotype.Service

@Service
class RegisterUserAccountService(
    private val accountNameIndex: AccountNameIndex,
    private val userAccountRepository: UserAccountRepository
) {

  @Throws(AccountOperationException::class)
  fun registerByPassword(accountName: AccountName, plainPassword: PlainPassword): AccountId {

    // アカウント名の重複チェック
    checkAccountNameNotUsed(accountName)

    // 作成したアカウントを保存
    val passwordAuthentication = PasswordAuthentication.fromPlainPassword(plainPassword)
    val (userAccount, userAccountEvent) = UserAccount.create(accountName, passwordAuthentication)
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