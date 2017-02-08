package com.github.rozyhead.balmn.board.domain.model.sheet

import java.util.*

data class SheetId(val uuid: UUID) {

  companion object {

    fun generate(): SheetId = SheetId(UUID.randomUUID())

  }

}
