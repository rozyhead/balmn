package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemorySheetRepository : SheetRepository {

  val events = mutableMapOf<SheetIdentifier, List<SheetEvent>>()

  override fun findByIdentifier(sheetIdentifier: SheetIdentifier): Pair<Sheet, List<SheetEvent>>? {
    val events = this.events[sheetIdentifier] ?: return null
    return Pair(events.fold(Sheet(), { a, b -> a apply b }), events)
  }

  override fun save(sheetIdentifier: SheetIdentifier, events: List<SheetEvent>, oldEvents: List<SheetEvent>) {
    this.events[sheetIdentifier] = oldEvents + events
  }

}