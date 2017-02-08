package com.github.rozyhead.balmn.board.domain.model.sheet

import com.github.rozyhead.balmn.board.domain.model.card.CardId

data class SheetCards(
    val cardIds: List<CardId> = emptyList()
) {

  fun contains(cardId: CardId): Boolean = cardIds.contains(cardId)

  fun addCard(cardId: CardId): SheetCards {
    require(!contains(cardId))
    return copy(cardIds = cardIds + cardId)
  }
}
