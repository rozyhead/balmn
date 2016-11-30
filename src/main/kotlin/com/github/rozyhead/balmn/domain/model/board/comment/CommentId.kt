package com.github.rozyhead.balmn.domain.model.board.comment

import java.util.*

data class CommentId(val uuid: UUID) {

  companion object {

    fun generate(): CommentId = CommentId(UUID.randomUUID())

  }

}
