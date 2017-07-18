package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.AccountOperationException
import com.github.rozyhead.balmn.account.domain.model.AccountType
import com.github.rozyhead.balmn.account.domain.model.authentication.password.PlainPassword
import com.github.rozyhead.balmn.account.port.adapter.index.memory.InMemoryAccountNameIndex
import com.github.rozyhead.balmn.account.port.adapter.repository.memory.InMemoryUserAccountRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class RegisterUserAccountServiceTest {

  val accountNameIndex = InMemoryAccountNameIndex()
  val userAccountRepository = InMemoryUserAccountRepository()

  val sut = RegisterUserAccountService(accountNameIndex, userAccountRepository)

  @Test
  fun registerByPassword() {
    val accountName = AccountName("test")
    val plainPassword = PlainPassword("secret")

    val accountId = sut.registerByPassword(accountName, plainPassword)

    val (account) = userAccountRepository.find(accountId)!!
    assertThat(account.accountName).isEqualTo(accountName)
    assertThat(accountNameIndex.find(accountName)).isEqualTo(Pair(accountId, AccountType.UserAccount))
  }

  @Test
  fun registerByPassword_when_accountName_already_exists() {
    val accountName = AccountName("test")
    accountNameIndex.save(accountName, AccountId.generate(), AccountType.UserAccount)

    val plainPassword = PlainPassword("secret")

    assertThatThrownBy { sut.registerByPassword(accountName, plainPassword) }
        .isInstanceOf(AccountOperationException.AccountNameAlreadyUsedException::class.java)
  }

}