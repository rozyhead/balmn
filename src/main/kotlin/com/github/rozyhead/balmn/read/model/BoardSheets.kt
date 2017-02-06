package com.github.rozyhead.balmn.read.model

import org.jetbrains.exposed.sql.Table

object BoardSheets : Table("board_sheets") {
  val accountName = varchar("account_name", 255)
  val boardName = varchar("board_name", 255)
  val seq = integer("seq")
  val sheetId = varchar("sheet_id", 36)
  val createdBy = varchar("created_by", 255)
  val createdAt = datetime("created_at")
  val modifiedBy = varchar("modified_by", 255)
  val modifiedAt = datetime("modified_at")
}