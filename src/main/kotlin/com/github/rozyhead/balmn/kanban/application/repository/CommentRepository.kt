package com.github.rozyhead.balmn.kanban.application.repository

import com.github.rozyhead.balmn.common.application.repository.DomainRepository
import com.github.rozyhead.balmn.kanban.domain.model.comment.Comment
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentEvent
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentId

interface CommentRepository : DomainRepository<CommentId, CommentEvent, Comment> {
}