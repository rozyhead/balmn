package com.github.rozyhead.balmn.board.domain.model.comment

import java.util.*

data class CommentId(val uuid: UUID) {

  companion object {

    fun generate(): CommentId = CommentId(UUID.randomUUID())

  }

}
