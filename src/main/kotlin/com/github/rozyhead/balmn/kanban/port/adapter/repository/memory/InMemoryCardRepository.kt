package com.github.rozyhead.balmn.kanban.port.adapter.repository.memory

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper
import com.github.rozyhead.balmn.kanban.application.repository.CardRepository
import com.github.rozyhead.balmn.kanban.domain.model.card.Card
import com.github.rozyhead.balmn.kanban.domain.model.card.CardEvent
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId

class InMemoryCardRepository : CardRepository {

  val helper = InMemoryRepositoryHelper<CardEvent, Card, CardId>(
      emptyEntity = Card()
  )

  override fun exists(id: CardId): Boolean
      = helper.existsInMemory(id)

  override fun find(id: CardId): Pair<Card, Version>?
      = helper.findByMemory(id)

  override fun save(id: CardId, version: Version, vararg additionalEvents: CardEvent)
      = helper.saveToMemory(id, version, *additionalEvents)

}