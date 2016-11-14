package com.github.rozyhead.balmn.domain.model.account

/**
 * @author takeshi
 */
interface UserAccountRepository {

  fun findByAccountName(accountName: AccountName): UserAccount?

  fun save(userAccount: UserAccount)

}
