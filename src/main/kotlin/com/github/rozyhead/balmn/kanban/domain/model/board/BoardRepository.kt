package com.github.rozyhead.balmn.kanban.domain.model.board

import com.github.rozyhead.balmn.common.domain.model.DomainRepository

interface BoardRepository : DomainRepository<BoardId, BoardEvent, Board> {
}