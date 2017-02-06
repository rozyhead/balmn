package com.github.rozyhead.balmn.read.model

import org.jetbrains.exposed.sql.Table

object Accounts : Table("accounts") {
  val accountName = varchar("account_name", 255).primaryKey()
  val groupAccount = bool("is_group_account").default(false)
}