package com.github.rozyhead.balmn.kanban.domain.model.board

data class BoardOwner(val value: String) {

  companion object {
    val empty = BoardOwner("")
  }

}
