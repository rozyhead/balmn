package com.github.rozyhead.balmn.domain.model.board.sheet

import java.util.*

data class SheetIdentifier(val uuid: UUID) {

  companion object {

    fun generate(): SheetIdentifier {
      return SheetIdentifier(UUID.randomUUID())
    }

  }

}
