package com.github.rozyhead.balmn.read.model

import org.jetbrains.exposed.sql.Table

object Comments : Table("comments") {
  val commentId = varchar("comment_id", 36).primaryKey()
  val cardId = varchar("card_id", 36)
  val commentContent = text("comment_content")
  val createdBy = varchar("created_by", 255)
  val createdAt = datetime("created_at")
  val modifiedBy = varchar("modified_by", 255)
  val modifiedAt = datetime("modified_at")
}