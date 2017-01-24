package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.board.card.Card
import com.github.rozyhead.balmn.domain.model.board.card.CardEvent
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.CardRepository
import com.github.rozyhead.balmn.util.ddd.Version

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