package com.github.rozyhead.balmn.kanban.domain.model.board

data class BoardName(val value: String) {

  companion object {

    val empty = BoardName("")

  }
}
