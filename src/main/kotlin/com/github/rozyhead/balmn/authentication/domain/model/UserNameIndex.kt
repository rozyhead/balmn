package com.github.rozyhead.balmn.authentication.domain.model

interface UserNameIndex {

  fun exists(userName: UserName): Boolean

  fun find(userName: UserName): UserId?

  fun save(userName: UserName, userId: UserId)

}