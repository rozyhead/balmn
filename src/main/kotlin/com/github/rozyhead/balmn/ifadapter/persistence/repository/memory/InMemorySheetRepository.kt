package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.service.repository.SheetRepository
import org.springframework.stereotype.Repository

@Repository
class InMemorySheetRepository : SheetRepository {

  val events = mutableMapOf<SheetId, List<SheetEvent>>()

  override fun findById(sheetId: SheetId): Pair<Sheet, List<SheetEvent>>? {
    val events = this.events[sheetId] ?: return null
    return Pair(events.fold(Sheet(), { a, b -> a apply b }), events)
  }

  override fun save(sheetId: SheetId, events: List<SheetEvent>, oldEvents: List<SheetEvent>) {
    this.events[sheetId] = oldEvents + events
  }

}