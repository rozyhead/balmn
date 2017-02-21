package com.github.rozyhead.balmn.kanban.domain.model.comment

import java.util.*

data class CommentId(val uuid: UUID) {

  companion object {

    fun generate(): CommentId = CommentId(UUID.randomUUID())

  }

}
