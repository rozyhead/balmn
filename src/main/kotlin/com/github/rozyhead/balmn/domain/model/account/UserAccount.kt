package com.github.rozyhead.balmn.domain.model.account

import com.github.rozyhead.balmn.domain.model.account.Account
import com.github.rozyhead.balmn.domain.model.account.AccountName

data class UserAccount(
    override val accountName: AccountName
) : Account