package com.github.rozyhead.balmn.kanban.domain.model.board

import com.github.rozyhead.balmn.kanban.domain.model.UserId

interface BoardOwnerService {

  fun exists(boardOwner: BoardOwner): Boolean

  fun isMember(boardOwner: BoardOwner, userId: UserId): Boolean

}