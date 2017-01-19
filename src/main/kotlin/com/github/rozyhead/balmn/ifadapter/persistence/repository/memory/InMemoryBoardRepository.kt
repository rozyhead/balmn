package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.service.repository.BoardRepository

class InMemoryBoardRepository : BoardRepository, AbstractInMemoryRepository<BoardEvent, Board, InMemoryBoardRepository.BoardId>() {

  data class BoardId(
      val accountName: AccountName,
      val boardName: BoardName
  )

  override val emptyEntity: Board
    get() = Board()

  override fun exists(accountName: AccountName, boardName: BoardName): Boolean = existsInMemory(BoardId(accountName, boardName))

  override fun findByAccountNameAndBoardName(accountName: AccountName, boardName: BoardName): Pair<Board, List<BoardEvent>>? = findByMemory(BoardId(accountName, boardName))

  override fun save(accountName: AccountName, boardName: BoardName, events: List<BoardEvent>, oldEvents: List<BoardEvent>) = saveToMemory(BoardId(accountName, boardName), events, oldEvents)

}