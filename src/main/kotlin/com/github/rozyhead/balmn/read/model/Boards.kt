package com.github.rozyhead.balmn.read.model

import org.jetbrains.exposed.sql.Table

object Boards : Table("boards") {
  val accountName = varchar("account_name", 255)
  val boardName = varchar("board_name", 255)
  val createdBy = varchar("created_by", 255)
  val createdAt = datetime("created_at")
  val modifiedBy = varchar("modified_by", 255)
  val modifiedAt = datetime("modified_at")
}