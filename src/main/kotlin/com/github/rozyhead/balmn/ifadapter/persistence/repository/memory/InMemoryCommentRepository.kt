package com.github.rozyhead.balmn.ifadapter.persistence.repository.memory

import com.github.rozyhead.balmn.domain.model.board.comment.Comment
import com.github.rozyhead.balmn.domain.model.board.comment.CommentId
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import com.github.rozyhead.balmn.service.repository.CommentRepository
import com.github.rozyhead.balmn.util.ddd.Version

class InMemoryCommentRepository : CommentRepository {

  val helper = InMemoryRepositoryHelper<CommentEvent, Comment, CommentId>(
      emptyEntity = Comment()
  )

  override fun save(commentId: CommentId, version: Version, vararg additionalEvents: CommentEvent)
      = helper.saveToMemory(commentId, version, *additionalEvents)

}