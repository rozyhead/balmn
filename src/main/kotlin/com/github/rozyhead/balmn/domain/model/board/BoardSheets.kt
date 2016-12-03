package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId

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
