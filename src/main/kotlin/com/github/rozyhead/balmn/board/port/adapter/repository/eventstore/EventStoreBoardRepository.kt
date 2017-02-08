package com.github.rozyhead.balmn.board.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.application.repository.BoardRepository
import com.github.rozyhead.balmn.board.domain.model.Board
import com.github.rozyhead.balmn.board.domain.model.BoardEvent
import com.github.rozyhead.balmn.board.domain.model.BoardName
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStoreBoardRepository(eventStore: EventStore) : BoardRepository {

  data class BoardId(
      val accountName: AccountName,
      val boardName: BoardName
  )

  val helper = EventStoreRepositoryHelper<BoardEvent, Board, BoardId>(
      eventStore = eventStore,
      eventClass = BoardEvent::class,
      emptyEntity = Board(),
      streamIdOf = { "Board(${it.accountName.value}, ${it.boardName.value}" }
  )

  override fun exists(accountName: AccountName, boardName: BoardName): Boolean
      = helper.existsInStore(BoardId(accountName, boardName))

  override fun findByAccountNameAndBoardName(accountName: AccountName, boardName: BoardName): Pair<Board, Version>?
      = helper.findByStore(BoardId(accountName, boardName))

  override fun save(accountName: AccountName, boardName: BoardName, version: Version, vararg additionalEvents: BoardEvent)
      = helper.saveToStore(BoardId(accountName, boardName), version, *additionalEvents)

}