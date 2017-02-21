package com.github.rozyhead.balmn.kanban.domain.model.board

import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId

data class BoardSheets(
    val sheetIds: List<SheetId> = emptyList()
) {

  companion object {
    val empty = BoardSheets()
  }

  fun contains(sheetId: SheetId): Boolean = sheetIds.contains(sheetId)

  fun addSheet(sheetId: SheetId): BoardSheets {
    require(!contains(sheetId))
    return copy(sheetIds = sheetIds + sheetId)
  }
}
