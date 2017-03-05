package com.github.rozyhead.balmn.kanban.port.adapter.repository.memory

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper
import com.github.rozyhead.balmn.kanban.application.repository.CommentRepository
import com.github.rozyhead.balmn.kanban.domain.model.comment.Comment
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentEvent
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentId

class InMemoryCommentRepository : CommentRepository {

  val helper = InMemoryRepositoryHelper<CommentEvent, Comment, CommentId>(
      emptyEntity = Comment()
  )

  override fun find(id: CommentId): Pair<Comment, Version>?
      = helper.findByMemory(id)

  override fun save(id: CommentId, version: Version, vararg additionalEvents: CommentEvent)
      = helper.saveToMemory(id, version, *additionalEvents)

}