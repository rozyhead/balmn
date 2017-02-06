package com.github.rozyhead.balmn.read.model

import org.jetbrains.exposed.sql.Table

object SheetCards : Table("sheet_cards") {
  val sheetId = varchar("sheet_id", 36)
  val seq = integer("seq")
  val cardId = varchar("card_id", 36)
  val createdBy = varchar("created_by", 255)
  val createdAt = datetime("created_at")
  val modifiedBy = varchar("modified_by", 255)
  val modifiedAt = datetime("modified_at")
}