package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.card.CardEvent
import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier
import com.github.rozyhead.balmn.domain.model.board.card.CardRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemoryCardRepository : CardRepository {

  val events = mutableMapOf<CardIdentifier, List<CardEvent>>()

  override fun exists(cardIdentifier: CardIdentifier): Boolean {
    return events.contains(cardIdentifier)
  }

  override fun save(cardIdentifier: CardIdentifier, events: List<CardEvent>, oldEvents: List<CardEvent>) {
    this.events[cardIdentifier] = oldEvents + events
  }

}