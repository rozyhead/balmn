package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName
import com.github.rozyhead.balmn.util.ddd.DomainEntity

data class Board(
    val identifier: BoardIdentifier = BoardIdentifier(AccountName(""), BoardName(""))
) : DomainEntity<BoardEvent, Board> {

  companion object {
    fun create(boardIdentifier: BoardIdentifier, createdBy: AccountName): Pair<Board, BoardCreated> {
      return Board() and BoardCreated(boardIdentifier, createdBy)
    }
  }

  val owner: AccountName = identifier.accountName
  val name: BoardName = identifier.boardName

  override fun <E> apply(event: E): Board = when (event) {
    is BoardCreated -> {
      copy(identifier = event.identifier)
    }
    else -> {
      throw IllegalArgumentException(event.toString())
    }
  }

}
