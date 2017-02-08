package com.github.rozyhead.balmn.board.port.adapter.repository.memory

import com.github.rozyhead.balmn.board.application.repository.SheetRepository
import com.github.rozyhead.balmn.board.domain.model.sheet.Sheet
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetEvent
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper

class InMemorySheetRepository : SheetRepository {

  val helper = InMemoryRepositoryHelper<SheetEvent, Sheet, SheetId>(
      emptyEntity = Sheet()
  )

  override fun findById(sheetId: SheetId): Pair<Sheet, Version>?
      = helper.findByMemory(sheetId)

  override fun save(sheetId: SheetId, version: Version, vararg additionalEvents: SheetEvent)
      = helper.saveToMemory(sheetId, version, *additionalEvents)

}