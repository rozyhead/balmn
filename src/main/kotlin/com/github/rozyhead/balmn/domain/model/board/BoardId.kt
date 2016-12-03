package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName

data class BoardId(
    val accountName: AccountName,
    val boardName: BoardName
) {

  companion object {
    val empty = BoardId(AccountName.empty, BoardName.empty)
  }

  override fun toString(): String {
    return "${accountName.value}/${boardName.value}"
  }

}
