package com.github.rozyhead.balmn.account.port.adapter.facade

interface AccountServiceFacade {

  fun exists(accountId: String): Boolean

  fun isMember(accountId: String, memberAccountId: String): Boolean

}