package com.github.rozyhead.balmn.account.port.adapter.facade.impl

import com.github.rozyhead.balmn.account.port.adapter.facade.AccountServiceFacade
import org.springframework.stereotype.Component

@Component
class AccountServiceFacadeImpl : AccountServiceFacade {

  override fun exists(accountId: String): Boolean {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun isMember(accountId: String, memberAccountId: String): Boolean {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}