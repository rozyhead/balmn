package com.github.rozyhead.balmn.kanban.domain.model.comment

data class CommentContent(val value: String) {

  companion object {
    val empty = CommentContent("")
  }

}
