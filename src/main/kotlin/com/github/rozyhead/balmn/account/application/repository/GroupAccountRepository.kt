package com.github.rozyhead.balmn.account.application.repository

import com.github.rozyhead.balmn.account.domain.model.AccountId
import com.github.rozyhead.balmn.account.domain.model.group.GroupAccount
import com.github.rozyhead.balmn.account.domain.model.group.GroupAccountEvent
import com.github.rozyhead.balmn.common.application.repository.DomainRepository

interface GroupAccountRepository : DomainRepository<AccountId, GroupAccountEvent, GroupAccount> {
}