package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId

interface SheetRepository {

  fun findById(sheetId: SheetId): Pair<Sheet, List<SheetEvent>>?

  fun save(sheetId: SheetId, events: List<SheetEvent>, oldEvents: List<SheetEvent>)

}