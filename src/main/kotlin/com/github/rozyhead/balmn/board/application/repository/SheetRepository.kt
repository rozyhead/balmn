package com.github.rozyhead.balmn.board.application.repository

import com.github.rozyhead.balmn.board.domain.model.sheet.Sheet
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetEvent
import com.github.rozyhead.balmn.board.domain.model.sheet.SheetId
import com.github.rozyhead.balmn.common.domain.model.Version

interface SheetRepository {

  fun findById(sheetId: SheetId): Pair<Sheet, Version>?

  fun save(sheetId: SheetId, version: Version, vararg additionalEvents: SheetEvent)

}