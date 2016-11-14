package com.github.rozyhead.balmn.usecase

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.UserAccount
import com.github.rozyhead.balmn.domain.model.authentication.PlainPassword
import org.springframework.transaction.annotation.Transactional

/**
 * @author takeshi
 */
interface LoginUsecase {

  data class Command(
      val accountName: AccountName,
      val plainPassword: PlainPassword
  )

  @Transactional(readOnly = true)
  @Throws(LoginFailureException::class)
  fun execute(command: Command): UserAccount?

}