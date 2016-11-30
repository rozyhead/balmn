package com.github.rozyhead.balmn.domain.model.board.sheet

import java.util.*

data class SheetId(val uuid: UUID) {

  companion object {

    fun generate(): SheetId = SheetId(UUID.randomUUID())

  }

}
