package com.github.rozyhead.balmn.board.domain.model.sheet

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.domain.model.card.CardId
import com.github.rozyhead.balmn.common.domain.model.DomainEntity

data class Sheet(
    val id: SheetId = SheetId.generate(),
    val name: SheetName = SheetName(""),
    val cards: SheetCards = SheetCards()
) : DomainEntity<SheetEvent, Sheet> {

  companion object {
    fun create(sheetName: SheetName, occurredBy: AccountName): Pair<Sheet, SheetEvent> {
      return Sheet() and SheetCreated(SheetId.Companion.generate(), sheetName, occurredBy = occurredBy)
    }
  }

  fun hasCard(cardId: CardId): Boolean {
    return cards.contains(cardId)
  }

  fun addCard(cardId: CardId, occurredBy: AccountName): Pair<Sheet, SheetEvent> {
    require(!cards.contains(cardId))
    return this and CardAdded(id, cardId, occurredBy = occurredBy)
  }

  override fun apply(event: SheetEvent): Sheet = when (event) {
    is SheetCreated -> {
      copy(id = event.sheetId, name = event.sheetName)
    }
    is CardAdded -> {
      copy(cards = cards.addCard(event.cardId))
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
