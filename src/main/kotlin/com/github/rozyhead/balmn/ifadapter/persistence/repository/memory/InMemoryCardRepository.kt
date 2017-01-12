package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.card.Card
import com.github.rozyhead.balmn.domain.model.board.card.CardEvent
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.service.repository.CardRepository

class InMemoryCardRepository : CardRepository, AbstractInMemoryRepository<CardEvent, Card, CardId>() {

  override val emptyEntity: Card
    get() = Card()

  override fun exists(cardId: CardId): Boolean = existsInMemory(cardId)

  override fun save(cardId: CardId, events: List<CardEvent>, oldEvents: List<CardEvent>) = saveToMemory(cardId, events, oldEvents)

}