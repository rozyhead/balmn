package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.comment.CommentId
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import com.github.rozyhead.balmn.service.repository.CommentRepository

class InMemoryCommentRepository : CommentRepository {

  val events = mutableMapOf<CommentId, List<CommentEvent>>()

  override fun save(commentId: CommentId, events: List<CommentEvent>, oldEvents: List<CommentEvent>) {
    this.events[commentId] = oldEvents + events
  }

}