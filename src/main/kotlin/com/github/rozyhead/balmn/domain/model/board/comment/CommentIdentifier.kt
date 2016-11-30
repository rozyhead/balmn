package com.github.rozyhead.balmn.domain.model.board.comment

import java.util.*

data class CommentIdentifier(val uuid: UUID) {

  companion object {

    fun generate(): CommentIdentifier = CommentIdentifier(UUID.randomUUID())

  }

}
