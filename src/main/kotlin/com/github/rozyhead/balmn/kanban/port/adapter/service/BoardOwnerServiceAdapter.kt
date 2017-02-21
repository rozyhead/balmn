package com.github.rozyhead.balmn.kanban.port.adapter.service

import com.github.rozyhead.balmn.account.port.adapter.facade.AccountServiceFacade
import com.github.rozyhead.balmn.kanban.domain.model.UserId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwner
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardOwnerService
import org.springframework.stereotype.Component

@Component
class BoardOwnerServiceAdapter(
    val accountServiceFacade: AccountServiceFacade
) : BoardOwnerService {

  override fun exists(boardOwner: BoardOwner): Boolean {
    return accountServiceFacade.exists(boardOwner.value)
  }

  override fun isMember(boardOwner: BoardOwner, userId: UserId): Boolean {
    return accountServiceFacade.isMember(boardOwner.value, userId.value)
  }

}