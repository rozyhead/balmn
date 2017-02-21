package com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.kanban.application.repository.CardRepository
import com.github.rozyhead.balmn.kanban.domain.model.card.Card
import com.github.rozyhead.balmn.kanban.domain.model.card.CardEvent
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId

class EventStoreCardRepository(eventStore: EventStore) : CardRepository {

  val helper = EventStoreRepositoryHelper<CardEvent, Card, CardId>(
      eventStore = eventStore,
      eventClass = CardEvent::class,
      emptyEntity = Card(),
      streamIdOf = { "Card-${it.uuid}" }
  )

  override fun exists(id: CardId): Boolean
      = helper.existsInStore(id)

  override fun find(id: CardId): Pair<Card, Version>?
      = helper.findByStore(id)

  override fun save(id: CardId, version: Version, vararg additionalEvents: CardEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}