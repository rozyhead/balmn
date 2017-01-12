package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.service.repository.SheetRepository

class InMemorySheetRepository : SheetRepository, AbstractInMemoryRepository<SheetEvent, Sheet, SheetId>() {

  override val emptyEntity: Sheet
    get() = Sheet()

  override fun findById(sheetId: SheetId): Pair<Sheet, List<SheetEvent>>? = findByMemory(sheetId)

  override fun save(sheetId: SheetId, events: List<SheetEvent>, oldEvents: List<SheetEvent>) = saveToMemory(sheetId, events, oldEvents)

}