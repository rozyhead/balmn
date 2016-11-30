package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.domain.model.board.card.CardId

data class SheetCards(
    val cardIds: List<CardId> = emptyList()
) {

  fun contains(cardId: CardId): Boolean = cardIds.contains(cardId)

  fun addCard(cardId: CardId): SheetCards {
    require(!contains(cardId))
    return copy(cardIds = cardIds + cardId)
  }
}
