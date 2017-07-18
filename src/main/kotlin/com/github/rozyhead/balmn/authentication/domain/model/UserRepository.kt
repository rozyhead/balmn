package com.github.rozyhead.balmn.authentication.domain.model

import com.github.rozyhead.balmn.common.domain.model.DomainRepository

interface UserRepository : DomainRepository<UserId, UserEvent, User> {
}