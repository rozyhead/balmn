package com.github.rozyhead.balmn.kanban.port.adapter.repository.memory

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper
import com.github.rozyhead.balmn.kanban.application.repository.SheetRepository
import com.github.rozyhead.balmn.kanban.domain.model.sheet.Sheet
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetEvent
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId

class InMemorySheetRepository : SheetRepository {

  val helper = InMemoryRepositoryHelper<SheetEvent, Sheet, SheetId>(
      emptyEntity = Sheet()
  )

  override fun exists(id: SheetId): Boolean
      = helper.existsInMemory(id)

  override fun find(id: SheetId): Pair<Sheet, Version>?
      = helper.findByMemory(id)

  override fun save(id: SheetId, version: Version, vararg additionalEvents: SheetEvent)
      = helper.saveToMemory(id, version, *additionalEvents)

}