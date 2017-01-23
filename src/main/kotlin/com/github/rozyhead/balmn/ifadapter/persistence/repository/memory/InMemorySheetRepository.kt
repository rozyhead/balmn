package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.service.repository.SheetRepository
import com.github.rozyhead.balmn.util.ddd.Version

class InMemorySheetRepository : SheetRepository {

  val helper = InMemoryRepositoryHelper<SheetEvent, Sheet, SheetId>(
      emptyEntity = Sheet()
  )

  override fun findById(sheetId: SheetId): Pair<Sheet, Version>?
      = helper.findByMemory(sheetId)

  override fun save(sheetId: SheetId, version: Version, vararg additionalEvents: SheetEvent)
      = helper.saveToMemory(sheetId, version, *additionalEvents)

}