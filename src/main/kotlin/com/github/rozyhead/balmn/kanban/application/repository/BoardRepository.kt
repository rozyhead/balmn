package com.github.rozyhead.balmn.kanban.application.repository

import com.github.rozyhead.balmn.common.application.repository.DomainRepository
import com.github.rozyhead.balmn.kanban.domain.model.board.Board
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardEvent
import com.github.rozyhead.balmn.kanban.domain.model.board.BoardId

interface BoardRepository : DomainRepository<BoardId, BoardEvent, Board> {
}