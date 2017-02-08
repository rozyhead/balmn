package com.github.rozyhead.balmn.board.domain.model.card

import java.util.*

data class CardId(val uuid: UUID) {

  companion object {

    fun generate(): CardId = CardId(UUID.randomUUID())

  }
}
