package com.github.rozyhead.balmn.board.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.board.application.repository.CommentRepository
import com.github.rozyhead.balmn.board.domain.model.comment.Comment
import com.github.rozyhead.balmn.board.domain.model.comment.CommentId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore

class EventStoreCommentRepository(eventStore: EventStore) : CommentRepository {

  val helper = EventStoreRepositoryHelper<CommentEvent, Comment, CommentId>(
      eventStore = eventStore,
      eventClass = CommentEvent::class,
      emptyEntity = Comment(),
      streamIdOf = { "Comment(${it.uuid}" }
  )

  override fun save(commentId: CommentId, version: Version, vararg additionalEvents: CommentEvent)
      = helper.saveToStore(commentId, version, *additionalEvents)

}