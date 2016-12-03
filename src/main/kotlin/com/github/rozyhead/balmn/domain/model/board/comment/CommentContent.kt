package com.github.rozyhead.balmn.domain.model.board.comment

data class CommentContent(val value: String) {

  companion object {
    val empty = CommentContent("")
  }

}
