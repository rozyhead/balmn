package com.github.rozyhead.balmn.board.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.board.application.repository.CardRepository
import com.github.rozyhead.balmn.board.domain.model.card.Card
import com.github.rozyhead.balmn.board.domain.model.card.CardEvent
import com.github.rozyhead.balmn.board.domain.model.card.CardId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStoreCardRepository(eventStore: EventStore) : CardRepository {

  val helper = EventStoreRepositoryHelper<CardEvent, Card, CardId>(
      eventStore = eventStore,
      eventClass = CardEvent::class,
      emptyEntity = Card(),
      streamIdOf = { "Card(${it.uuid}" }
  )

  override fun exists(cardId: CardId): Boolean
      = helper.existsInStore(cardId)

  override fun save(cardId: CardId, version: Version, vararg additionalEvents: CardEvent)
      = helper.saveToStore(cardId, version, *additionalEvents)

}