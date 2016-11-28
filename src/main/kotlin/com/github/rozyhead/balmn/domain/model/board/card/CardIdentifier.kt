package com.github.rozyhead.balmn.domain.model.board.card

import java.util.*

data class CardIdentifier(val uuid: UUID) {

  companion object {

    fun generate(): CardIdentifier = CardIdentifier(UUID.randomUUID())

  }
}
