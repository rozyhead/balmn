package com.github.rozyhead.balmn.board.port.adapter.repository.memory

import com.github.rozyhead.balmn.board.application.repository.CommentRepository
import com.github.rozyhead.balmn.board.domain.model.comment.Comment
import com.github.rozyhead.balmn.board.domain.model.comment.CommentId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.memory.InMemoryRepositoryHelper
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent

class InMemoryCommentRepository : CommentRepository {

  val helper = InMemoryRepositoryHelper<CommentEvent, Comment, CommentId>(
      emptyEntity = Comment()
  )

  override fun save(commentId: CommentId, version: Version, vararg additionalEvents: CommentEvent)
      = helper.saveToMemory(commentId, version, *additionalEvents)

}