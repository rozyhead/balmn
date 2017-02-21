package com.github.rozyhead.balmn.authentication.application.repository

import com.github.rozyhead.balmn.authentication.domain.model.User
import com.github.rozyhead.balmn.authentication.domain.model.UserEvent
import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.common.application.repository.DomainRepository

interface UserRepository : DomainRepository<UserId, UserEvent, User> {
}