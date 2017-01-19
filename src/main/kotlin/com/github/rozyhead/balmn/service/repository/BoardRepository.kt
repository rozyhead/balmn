package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.Board
import com.github.rozyhead.balmn.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.domain.model.board.BoardName
import com.github.rozyhead.balmn.util.ddd.Version

interface BoardRepository {

  fun exists(accountName: AccountName, boardName: BoardName): Boolean

  fun findByAccountNameAndBoardName(accountName: AccountName, boardName: BoardName): Pair<Board, Version>?

  fun save(accountName: AccountName, boardName: BoardName, version: Version, vararg additionalEvents: BoardEvent)

}