package com.github.rozyhead.balmn.domain.model.board.sheet

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier
import com.github.rozyhead.balmn.util.ddd.DomainEntity
import java.util.*

data class Sheet(
    val identifier: SheetIdentifier = SheetIdentifier.generate(),
    val name: SheetName = SheetName(""),
    val cards: SheetCards = SheetCards()
) : DomainEntity<SheetEvent, Sheet> {

  companion object {
    fun create(sheetName: SheetName, occurredBy: AccountName): Pair<Sheet, SheetCreated> {
      return Sheet() and SheetCreated(SheetIdentifier.generate(), sheetName, occurredBy = occurredBy)
    }
  }

  fun addCard(cardIdentifier: CardIdentifier, occurredBy: AccountName): Pair<Sheet, CardAdded> {
    require(!cards.contains(cardIdentifier))
    return this and CardAdded(identifier, cardIdentifier, occurredBy = occurredBy)
  }

  override fun <E> apply(event: E): Sheet = when (event) {
    is SheetCreated -> {
      copy(identifier = event.sheetIdentifier, name = event.sheetName)
    }
    is CardAdded -> {
      copy(cards = cards.addCard(event.cardIdentifier))
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
