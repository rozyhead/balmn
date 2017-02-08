package com.github.rozyhead.balmn.board.port.adapter.repository.memory

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.application.repository.BoardRepository
import com.github.rozyhead.balmn.board.domain.model.Board
import com.github.rozyhead.balmn.board.domain.model.BoardEvent
import com.github.rozyhead.balmn.board.domain.model.BoardName
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper

class InMemoryBoardRepository : BoardRepository {

  data class BoardId(
      val accountName: AccountName,
      val boardName: BoardName
  )

  val helper = InMemoryRepositoryHelper<BoardEvent, Board, BoardId>(
      emptyEntity = Board()
  )

  override fun exists(accountName: AccountName, boardName: BoardName): Boolean
      = helper.existsInMemory(BoardId(accountName, boardName))

  override fun findByAccountNameAndBoardName(accountName: AccountName, boardName: BoardName): Pair<Board, Version>?
      = helper.findByMemory(BoardId(accountName, boardName))

  override fun save(accountName: AccountName, boardName: BoardName, version: Version, vararg additionalEvents: BoardEvent)
      = helper.saveToMemory(BoardId(accountName, boardName), version, *additionalEvents)

}