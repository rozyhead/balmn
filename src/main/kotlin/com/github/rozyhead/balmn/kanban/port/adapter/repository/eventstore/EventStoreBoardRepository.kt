package com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.kanban.application.repository.BoardRepository
import com.github.rozyhead.balmn.kanban.domain.model.board.Board
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId

class EventStoreBoardRepository(eventStore: EventStore) : BoardRepository {

  val helper = EventStoreRepositoryHelper<BoardEvent, Board, BoardId>(
      eventStore = eventStore,
      eventClass = BoardEvent::class,
      emptyEntity = Board(),
      streamIdOf = { "Board-${it.uuid}" }
  )

  override fun exists(id: BoardId): Boolean
      = helper.existsInStore(id)

  override fun find(id: BoardId): Pair<Board, Version>?
      = helper.findByStore(id)

  override fun save(id: BoardId, version: Version, vararg additionalEvents: BoardEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}