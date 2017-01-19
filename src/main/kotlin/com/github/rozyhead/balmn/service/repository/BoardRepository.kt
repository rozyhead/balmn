package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardName

interface BoardRepository {

  fun exists(accountName: AccountName, boardName: BoardName): Boolean

  fun findByAccountNameAndBoardName(accountName: AccountName, boardName: BoardName): Pair<Board, List<BoardEvent>>?

  fun save(accountName: AccountName, boardName: BoardName, events: List<BoardEvent>, oldEvents: List<BoardEvent>)

}