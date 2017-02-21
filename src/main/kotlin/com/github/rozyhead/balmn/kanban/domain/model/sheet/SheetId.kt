package com.github.rozyhead.balmn.kanban.domain.model.sheet

import java.util.*

data class SheetId(val uuid: UUID) {

  companion object {

    fun generate(): SheetId = SheetId(UUID.randomUUID())

  }

}
