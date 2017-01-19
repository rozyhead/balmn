package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.service.repository.SheetRepository
import com.github.rozyhead.balmn.util.ddd.Version

class InMemorySheetRepository : SheetRepository, AbstractInMemoryRepository<SheetEvent, Sheet, SheetId>() {

  override val emptyEntity: Sheet
    get() = Sheet()

  override fun findById(sheetId: SheetId): Pair<Sheet, Version>? = findByMemory(sheetId)

  override fun save(sheetId: SheetId, version: Version, vararg additionalEvents: SheetEvent) = saveToMemory(sheetId, version, *additionalEvents)

}