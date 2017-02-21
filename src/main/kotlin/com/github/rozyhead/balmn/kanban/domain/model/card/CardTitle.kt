package com.github.rozyhead.balmn.kanban.domain.model.card

data class CardTitle(val value: String) {

  companion object {
    val empty = CardTitle("")
  }

}
