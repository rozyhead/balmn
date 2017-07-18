package com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.msemys.esjc.EventStore
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.kanban.domain.model.board.Board
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardRepository

class EventStoreBoardRepository(
    eventStore: EventStore,
    objectMapper: ObjectMapper
) : BoardRepository {

  val helper = EventStoreRepositoryHelper<BoardEvent, Board, BoardId>(
      eventStore = eventStore,
      objectMapper = objectMapper,
      emptyEntity = Board(),
      streamIdOf = { "Board-${it.uuid}" }
  )

  override fun find(id: BoardId): Pair<Board, Version>?
      = helper.findByStore(id)

  override fun save(id: BoardId, version: Version, vararg additionalEvents: BoardEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}