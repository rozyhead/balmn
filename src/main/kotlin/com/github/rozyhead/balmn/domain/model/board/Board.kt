package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.util.ddd.DomainEntity

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

  fun allowSheetAdditionByUser(userAccount: UserAccount): Boolean {
    // TODO
    return true
  }

  fun allowCardAdditionByUser(userAccount: UserAccount): Boolean {
    // TODO
    return true
  }

  fun allowCommentAdditionByUser(userAccount: UserAccount): Boolean {
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
