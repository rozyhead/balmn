package com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.msemys.esjc.EventStore
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.kanban.domain.model.card.Card
import com.github.rozyhead.balmn.kanban.domain.model.card.CardEvent
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId
import com.github.rozyhead.balmn.kanban.domain.model.card.CardRepository

class EventStoreCardRepository(
    eventStore: EventStore,
    objectMapper: ObjectMapper
) : CardRepository {

  val helper = EventStoreRepositoryHelper<CardEvent, Card, CardId>(
      eventStore = eventStore,
      objectMapper = objectMapper,
      emptyEntity = Card(),
      streamIdOf = { "Card-${it.uuid}" }
  )

  override fun find(id: CardId): Pair<Card, Version>?
      = helper.findByStore(id)

  override fun save(id: CardId, version: Version, vararg additionalEvents: CardEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}