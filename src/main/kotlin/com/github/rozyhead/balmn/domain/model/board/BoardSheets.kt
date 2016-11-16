package com.github.rozyhead.balmn.domain.model.board

import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier

data class BoardSheets(
    val sheetIdentifiers: List<SheetIdentifier> = emptyList()
) {

  fun contains(sheetIdentifier: SheetIdentifier): Boolean = sheetIdentifiers.contains(sheetIdentifier)

  fun addSheet(sheetIdentifier: SheetIdentifier): BoardSheets {
    require(!sheetIdentifiers.contains(sheetIdentifier))
    return copy(sheetIdentifiers = sheetIdentifiers + sheetIdentifier)
  }
}
