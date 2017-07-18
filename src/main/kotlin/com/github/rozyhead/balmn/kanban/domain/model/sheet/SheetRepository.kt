package com.github.rozyhead.balmn.kanban.domain.model.sheet

import com.github.rozyhead.balmn.common.domain.model.DomainRepository

interface SheetRepository : DomainRepository<SheetId, SheetEvent, Sheet> {
}