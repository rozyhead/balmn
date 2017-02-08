package com.github.rozyhead.balmn.account.application.repository

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountEvent
import com.github.rozyhead.balmn.common.domain.model.Version

/**
 * @author takeshi
 */
interface UserAccountRepository {

  fun exists(accountName: AccountName): Boolean

  fun findByAccountName(accountName: AccountName): Pair<UserAccount, Version>?

  fun save(accountName: AccountName, version: Version, vararg additionalEvents: UserAccountEvent)

}
