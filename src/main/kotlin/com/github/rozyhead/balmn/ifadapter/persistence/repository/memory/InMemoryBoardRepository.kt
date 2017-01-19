package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.service.repository.BoardRepository
import com.github.rozyhead.balmn.util.ddd.Version

class InMemoryBoardRepository : BoardRepository, AbstractInMemoryRepository<BoardEvent, Board, InMemoryBoardRepository.BoardId>() {

  data class BoardId(
      val accountName: AccountName,
      val boardName: BoardName
  )

  override val emptyEntity: Board
    get() = Board()

  override fun exists(accountName: AccountName, boardName: BoardName): Boolean
      = existsInMemory(BoardId(accountName, boardName))

  override fun findByAccountNameAndBoardName(accountName: AccountName, boardName: BoardName): Pair<Board, Version>?
      = findByMemory(BoardId(accountName, boardName))

  override fun save(accountName: AccountName, boardName: BoardName, version: Version, vararg additionalEvents: BoardEvent)
      = saveToMemory(BoardId(accountName, boardName), version, *additionalEvents)

}