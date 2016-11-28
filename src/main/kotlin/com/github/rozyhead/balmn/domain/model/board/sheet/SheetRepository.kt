package com.github.rozyhead.balmn.domain.model.board.sheet

interface SheetRepository {

  fun findByIdentifier(sheetIdentifier: SheetIdentifier): Pair<Sheet, List<SheetEvent>>?

  fun save(sheetIdentifier: SheetIdentifier, events: List<SheetEvent>, oldEvents: List<SheetEvent>)

}