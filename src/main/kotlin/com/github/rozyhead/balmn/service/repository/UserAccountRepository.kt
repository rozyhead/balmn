package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent
import com.github.rozyhead.balmn.util.ddd.Version

/**
 * @author takeshi
 */
interface UserAccountRepository {

  fun exists(accountName: AccountName): Boolean

  fun findByAccountName(accountName: AccountName): Pair<UserAccount, Version>?

  fun save(accountName: AccountName, version: Version, vararg additionalEvents: UserAccountEvent)

}
