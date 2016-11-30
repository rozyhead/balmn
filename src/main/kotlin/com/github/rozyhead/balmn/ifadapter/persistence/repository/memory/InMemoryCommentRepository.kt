package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.comment.CommentIdentifier
import com.github.rozyhead.balmn.domain.model.board.comment.CommentRepository
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import org.springframework.stereotype.Repository

@Repository
open class InMemoryCommentRepository : CommentRepository {

  val events = mutableMapOf<CommentIdentifier, List<CommentEvent>>()

  override fun save(commentIdentifier: CommentIdentifier, events: List<CommentEvent>, oldEvents: List<CommentEvent>) {
    this.events[commentIdentifier] = oldEvents + events
  }

}