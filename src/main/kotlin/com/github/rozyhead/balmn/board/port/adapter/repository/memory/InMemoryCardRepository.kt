package com.github.rozyhead.balmn.board.port.adapter.repository.memory

import com.github.rozyhead.balmn.board.application.repository.CardRepository
import com.github.rozyhead.balmn.board.domain.model.card.Card
import com.github.rozyhead.balmn.board.domain.model.card.CardEvent
import com.github.rozyhead.balmn.board.domain.model.card.CardId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper

class InMemoryCardRepository : CardRepository {

  val helper = InMemoryRepositoryHelper<CardEvent, Card, CardId>(
      emptyEntity = Card()
  )

  override fun exists(cardId: CardId): Boolean
      = helper.existsInMemory(cardId)

  override fun save(cardId: CardId, version: Version, vararg additionalEvents: CardEvent)
      = helper.saveToMemory(cardId, version, *additionalEvents)

}