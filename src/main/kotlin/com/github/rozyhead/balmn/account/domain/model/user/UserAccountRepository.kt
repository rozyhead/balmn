package com.github.rozyhead.balmn.account.domain.model.user

import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.common.domain.model.DomainRepository

interface UserAccountRepository : DomainRepository<AccountId, UserAccountEvent, UserAccount> {
}
