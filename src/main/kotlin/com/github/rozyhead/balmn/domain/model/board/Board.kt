package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier
import com.github.rozyhead.balmn.util.ddd.DomainEntity

data class Board(
    val identifier: BoardIdentifier = BoardIdentifier(AccountName(""), BoardName("")),
    val sheets: BoardSheets = BoardSheets()
) : DomainEntity<BoardEvent, Board> {

  companion object {
    fun create(boardIdentifier: BoardIdentifier, occurredBy: AccountName): Pair<Board, BoardCreated> {
      return Board() and BoardCreated(boardIdentifier, occurredBy = occurredBy)
    }
  }

  val owner: AccountName = identifier.accountName
  val name: BoardName = identifier.boardName

  fun addSheet(sheetIdentifier: SheetIdentifier, occurredBy: AccountName): Pair<Board, SheetAdded> {
    require(!sheets.contains(sheetIdentifier))
    return this and SheetAdded(identifier, sheetIdentifier, occurredBy = occurredBy)
  }

  override fun <E> apply(event: E): Board = when (event) {
    is BoardCreated -> {
      copy(identifier = event.boardIdentifier)
    }
    is SheetAdded -> {
      copy(sheets = sheets.addSheet(event.sheetIdentifier))
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
