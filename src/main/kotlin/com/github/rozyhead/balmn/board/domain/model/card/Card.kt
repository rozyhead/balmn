package com.github.rozyhead.balmn.board.domain.model.card

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.common.domain.model.DomainEntity

data class Card(
    val id: CardId = CardId.generate(),
    val title: CardTitle = CardTitle.empty
) : DomainEntity<CardEvent, Card> {

  companion object {
    fun create(cardTitle: CardTitle, occurredBy: AccountName): Pair<Card, CardEvent> {
      return Card() and CardCreated(CardId.Companion.generate(), cardTitle, occurredBy = occurredBy)
    }
  }

  override fun apply(event: CardEvent): Card = when (event) {
    is CardCreated -> {
      copy(id = event.cardId, title = event.cardTitle)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
