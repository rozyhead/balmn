package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier

data class SheetCards(
    val cardIdentifiers: List<CardIdentifier> = emptyList()
) {

  fun contains(cardIdentifier: CardIdentifier): Boolean = cardIdentifiers.contains(cardIdentifier)

  fun addCard(cardIdentifier: CardIdentifier): SheetCards {
    require(!contains(cardIdentifier))
    return copy(cardIdentifiers = cardIdentifiers + cardIdentifier)
  }
}
