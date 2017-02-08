package com.github.rozyhead.balmn.board.domain.model

import com.github.rozyhead.balmn.account.domain.model.AccountName
import com.github.rozyhead.balmn.account.domain.model.user.UserAccount
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.common.domain.model.DomainEntity

data class Board(
    val accountName: AccountName = AccountName.empty,
    val boardName: BoardName = BoardName.empty,
    val sheets: BoardSheets = BoardSheets.empty
) : DomainEntity<BoardEvent, Board> {

  companion object {
    fun create(accountName: AccountName, boardName: BoardName, occurredBy: AccountName): Pair<Board, BoardEvent> {
      return Board() and BoardCreated(accountName, boardName, occurredBy = occurredBy)
    }
  }

  fun allowSheetAdditionByUser(@Suppress("UNUSED_PARAMETER") userAccount: UserAccount): Boolean {
    // TODO
    return true
  }

  fun allowCardAdditionByUser(@Suppress("UNUSED_PARAMETER") userAccount: UserAccount): Boolean {
    // TODO
    return true
  }

  fun allowCommentAdditionByUser(@Suppress("UNUSED_PARAMETER") userAccount: UserAccount): Boolean {
    // TODO
    return true
  }

  fun hasSheet(sheetId: SheetId): Boolean {
    return sheets.contains(sheetId)
  }

  fun addSheet(sheetId: SheetId, occurredBy: AccountName): Pair<Board, BoardEvent> {
    require(!sheets.contains(sheetId))
    return this and SheetAdded(accountName, boardName, sheetId, occurredBy = occurredBy)
  }

  override fun apply(event: BoardEvent): Board = when (event) {
    is BoardCreated -> {
      copy(accountName = event.accountName, boardName = event.boardName)
    }
    is SheetAdded -> {
      copy(sheets = sheets.addSheet(event.sheetId))
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
