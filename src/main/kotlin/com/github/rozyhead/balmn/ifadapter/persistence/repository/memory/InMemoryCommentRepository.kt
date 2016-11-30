package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.comment.CommentId
import com.github.rozyhead.balmn.domain.model.board.comment.CommentRepository
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import org.springframework.stereotype.Repository

@Repository
open class InMemoryCommentRepository : CommentRepository {

  val events = mutableMapOf<CommentId, List<CommentEvent>>()

  override fun save(commentId: CommentId, events: List<CommentEvent>, oldEvents: List<CommentEvent>) {
    this.events[commentId] = oldEvents + events
  }

}