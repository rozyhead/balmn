package com.github.rozyhead.balmn.domain.model.board.sheet

interface SheetRepository {

  fun save(sheetIdentifier: SheetIdentifier, events: List<SheetEvent>, oldEvents: List<SheetEvent>)

}