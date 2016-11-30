package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.card.CardEvent
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.service.repository.CardRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryCardRepository : CardRepository {

  val events = mutableMapOf<CardId, List<CardEvent>>()

  override fun exists(cardId: CardId): Boolean {
    return events.contains(cardId)
  }

  override fun save(cardId: CardId, events: List<CardEvent>, oldEvents: List<CardEvent>) {
    this.events[cardId] = oldEvents + events
  }

}