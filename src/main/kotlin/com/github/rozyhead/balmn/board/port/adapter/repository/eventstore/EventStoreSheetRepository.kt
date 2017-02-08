package com.github.rozyhead.balmn.board.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.board.application.repository.SheetRepository
import com.github.rozyhead.balmn.board.domain.model.sheet.Sheet
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetEvent
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStoreSheetRepository(eventStore: EventStore) : SheetRepository {

  val helper = EventStoreRepositoryHelper<SheetEvent, Sheet, SheetId>(
      eventStore = eventStore,
      eventClass = SheetEvent::class,
      emptyEntity = Sheet(),
      streamIdOf = { "Sheet(${it.uuid}" }
  )

  override fun findById(sheetId: SheetId): Pair<Sheet, Version>?
      = helper.findByStore(sheetId)

  override fun save(sheetId: SheetId, version: Version, vararg additionalEvents: SheetEvent)
      = helper.saveToStore(sheetId, version, *additionalEvents)

}