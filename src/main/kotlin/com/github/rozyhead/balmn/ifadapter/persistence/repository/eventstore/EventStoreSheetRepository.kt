package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.SheetRepository
import com.github.rozyhead.balmn.util.ddd.Version

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