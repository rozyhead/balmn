package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.board.sheet.Sheet
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetEvent
import com.github.rozyhead.balmn.domain.model.board.sheet.SheetId
import com.github.rozyhead.balmn.util.ddd.Version

interface SheetRepository {

  fun findById(sheetId: SheetId): Pair<Sheet, Version>?

  fun save(sheetId: SheetId, version: Version, vararg additionalEvents: SheetEvent)

}