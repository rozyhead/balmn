package com.github.rozyhead.balmn.kanban.application.repository

import com.github.rozyhead.balmn.common.application.repository.DomainRepository
import com.github.rozyhead.balmn.kanban.domain.model.sheet.Sheet
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetEvent
import com.github.rozyhead.balmn.kanban.domain.model.sheet.SheetId

interface SheetRepository : DomainRepository<SheetId, SheetEvent, Sheet> {
}