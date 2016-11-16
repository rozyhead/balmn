package com.github.rozyhead.balmn.domain.model.account.user

import com.github.rozyhead.balmn.domain.model.account.AccountName

/**
 * @author takeshi
 */
interface UserAccountRepository {

  fun findByAccountName(accountName: AccountName): Pair<UserAccount, List<UserAccountEvent>>?

  fun save(accountName: AccountName, events: List<UserAccountEvent>, oldEvents: List<UserAccountEvent>)

}
