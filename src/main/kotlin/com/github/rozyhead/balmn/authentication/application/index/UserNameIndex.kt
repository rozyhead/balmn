package com.github.rozyhead.balmn.authentication.application.index

import com.github.rozyhead.balmn.authentication.domain.model.UserId
import com.github.rozyhead.balmn.authentication.domain.model.UserName

interface UserNameIndex {

  fun exists(userName: UserName): Boolean

  fun find(userName: UserName): UserId?

}