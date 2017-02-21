package com.github.rozyhead.balmn.account.application.repository

import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.account.domain.model.user.UserAccountEvent
import com.github.rozyhead.balmn.common.application.repository.DomainRepository

interface UserAccountRepository : DomainRepository<AccountId, UserAccountEvent, UserAccount> {
}
