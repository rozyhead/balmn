package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.account.user.UserAccountEvent

/**
 * @author takeshi
 */
interface UserAccountRepository {

  fun findByAccountName(accountName: AccountName): Pair<UserAccount, List<UserAccountEvent>>?

  fun save(accountName: AccountName, events: List<UserAccountEvent>, oldEvents: List<UserAccountEvent>)

}
