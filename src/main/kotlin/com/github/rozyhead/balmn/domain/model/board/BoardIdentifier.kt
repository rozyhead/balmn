package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.account.AccountName

data class BoardIdentifier(
    val accountName: AccountName,
    val boardName: BoardName
)
