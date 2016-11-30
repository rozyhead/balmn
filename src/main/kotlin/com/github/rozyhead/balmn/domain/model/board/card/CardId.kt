package com.github.rozyhead.balmn.domain.model.board.card

import java.util.*

data class CardId(val uuid: UUID) {

  companion object {

    fun generate(): CardId = CardId(UUID.randomUUID())

  }
}
