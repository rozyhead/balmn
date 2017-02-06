package com.github.rozyhead.balmn.read.model

import org.jetbrains.exposed.sql.Table

object Cards : Table("cards") {
  val cardId = varchar("card_id", 36).primaryKey()
  val cardTitle = varchar("card_name", 255)
  val createdBy = varchar("created_by", 255)
  val createdAt = datetime("created_at")
  val modifiedBy = varchar("modified_by", 255)
  val modifiedAt = datetime("modified_at")
}