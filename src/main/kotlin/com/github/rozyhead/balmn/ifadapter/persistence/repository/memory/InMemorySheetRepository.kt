package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetIdentifier
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetRepository
import org.springframework.stereotype.Repository

@Repository
open class InMemorySheetRepository : SheetRepository {

  val events = mutableMapOf<SheetIdentifier, List<SheetEvent>>()

  override fun save(boardIdentifier: SheetIdentifier, events: List<SheetEvent>, oldEvents: List<SheetEvent>) {
    this.events[boardIdentifier] = oldEvents + events
  }

}