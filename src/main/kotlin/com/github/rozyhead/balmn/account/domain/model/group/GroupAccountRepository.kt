package com.github.rozyhead.balmn.account.domain.model.group

import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.common.domain.model.DomainRepository

interface GroupAccountRepository : DomainRepository<AccountId, GroupAccountEvent, GroupAccount> {
}