package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.BoardRepository
import com.github.rozyhead.balmn.util.ddd.Version

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