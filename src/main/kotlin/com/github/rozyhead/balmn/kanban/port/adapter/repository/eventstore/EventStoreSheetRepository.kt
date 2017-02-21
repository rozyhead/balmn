package com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.kanban.application.repository.SheetRepository
import com.github.rozyhead.balmn.kanban.domain.model.sheet.Sheet
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetEvent
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId

class EventStoreSheetRepository(eventStore: EventStore) : SheetRepository {

  val helper = EventStoreRepositoryHelper<SheetEvent, Sheet, SheetId>(
      eventStore = eventStore,
      eventClass = SheetEvent::class,
      emptyEntity = Sheet(),
      streamIdOf = { "Sheet-${it.uuid}" }
  )

  override fun exists(id: SheetId): Boolean
      = helper.existsInStore(id)

  override fun find(id: SheetId): Pair<Sheet, Version>?
      = helper.findByStore(id)

  override fun save(id: SheetId, version: Version, vararg additionalEvents: SheetEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}