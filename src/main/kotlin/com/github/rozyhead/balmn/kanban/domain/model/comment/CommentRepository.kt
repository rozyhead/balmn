package com.github.rozyhead.balmn.kanban.domain.model.comment

import com.github.rozyhead.balmn.common.domain.model.DomainRepository

interface CommentRepository : DomainRepository<CommentId, CommentEvent, Comment> {
}