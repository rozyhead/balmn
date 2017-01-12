package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.comment.Comment
import com.github.rozyhead.balmn.domain.model.board.comment.CommentId
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import com.github.rozyhead.balmn.service.repository.CommentRepository

class InMemoryCommentRepository : CommentRepository, AbstractInMemoryRepository<CommentEvent, Comment, CommentId>() {

  override val emptyEntity: Comment
    get() = Comment()

  override fun save(commentId: CommentId, events: List<CommentEvent>, oldEvents: List<CommentEvent>) = saveToMemory(commentId, events, oldEvents)

}