package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName

data class Board(
    val identifier: BoardIdentifier
) {

  val owner: AccountName = identifier.accountName
  val name: BoardName = identifier.boardName

}
