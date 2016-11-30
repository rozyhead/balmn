package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.account.user.UserAccount
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.util.ddd.DomainEntity

data class Board(
    val id: BoardId = BoardId(AccountName(""), BoardName("")),
    val boardName: BoardName = BoardName(""),
    val sheets: BoardSheets = BoardSheets()
) : DomainEntity<BoardEvent, Board> {

  companion object {
    fun create(boardId: BoardId, boardName: BoardName, occurredBy: AccountName): Pair<Board, BoardCreated> {
      return Board() and BoardCreated(boardId, boardName, occurredBy = occurredBy)
    }
  }

  val owner: AccountName = id.accountName
  val name: BoardName = id.boardName

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

  fun addSheet(sheetId: SheetId, occurredBy: AccountName): Pair<Board, SheetAdded> {
    require(!sheets.contains(sheetId))
    return this and SheetAdded(id, sheetId, occurredBy = occurredBy)
  }

  override fun <E> apply(event: E): Board = when (event) {
    is BoardCreated -> {
      copy(id = event.boardId, boardName = event.boardName)
    }
    is SheetAdded -> {
      copy(sheets = sheets.addSheet(event.sheetId))
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
