package com.github.rozyhead.balmn.board.application.repository

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.board.domain.model.Board
import com.github.rozyhead.balmn.board.domain.model.BoardEvent
import com.github.rozyhead.balmn.board.domain.model.BoardName
import com.github.rozyhead.balmn.common.domain.model.Version

interface BoardRepository {

  fun exists(accountName: AccountName, boardName: BoardName): Boolean

  fun findByAccountNameAndBoardName(accountName: AccountName, boardName: BoardName): Pair<Board, Version>?

  fun save(accountName: AccountName, boardName: BoardName, version: Version, vararg additionalEvents: BoardEvent)

}