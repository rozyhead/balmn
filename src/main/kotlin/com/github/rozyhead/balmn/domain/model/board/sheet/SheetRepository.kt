package com.github.rozyhead.balmn.domain.model.board.sheet

interface SheetRepository {

  fun findById(sheetId: SheetId): Pair<Sheet, List<SheetEvent>>?

  fun save(sheetId: SheetId, events: List<SheetEvent>, oldEvents: List<SheetEvent>)

}