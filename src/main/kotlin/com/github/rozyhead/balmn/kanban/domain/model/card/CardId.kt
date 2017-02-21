package com.github.rozyhead.balmn.kanban.domain.model.card

import java.util.*

data class CardId(val uuid: UUID) {

  companion object {

    fun generate(): CardId = CardId(UUID.randomUUID())

  }
}
