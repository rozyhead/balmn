package com.github.rozyhead.balmn.domain.model.board.card

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEntity
import java.util.*

data class Card(
    val id: CardId = CardId.generate(),
    val title: CardTitle = CardTitle("")
) : DomainEntity<CardEvent, Card> {

  companion object {
    fun create(cardTitle: CardTitle, occurredBy: AccountName): Pair<Card, CardCreated> {
      return Card() and CardCreated(CardId.generate(), cardTitle, occurredBy = occurredBy)
    }
  }

  override fun <E> apply(event: E): Card = when (event) {
    is CardCreated -> {
      copy(id = event.cardId, title = event.cardTitle)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
