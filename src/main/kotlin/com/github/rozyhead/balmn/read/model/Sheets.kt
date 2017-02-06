package com.github.rozyhead.balmn.read.model

import org.jetbrains.exposed.sql.Table

object Sheets : Table("sheets") {
  val sheetId = varchar("sheet_id", 36).primaryKey()
  val sheetName = varchar("sheet_name", 255)
  val createdBy = varchar("created_by", 255)
  val createdAt = datetime("created_at")
  val modifiedBy = varchar("modified_by", 255)
  val modifiedAt = datetime("modified_at")
}