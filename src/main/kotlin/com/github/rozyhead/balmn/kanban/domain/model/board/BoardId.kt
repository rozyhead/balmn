package com.github.rozyhead.balmn.kanban.domain.model.board

import java.util.*

data class BoardId(val uuid: UUID) {

  companion object {

    fun generate(): BoardId = BoardId(UUID.randomUUID())

  }
}
