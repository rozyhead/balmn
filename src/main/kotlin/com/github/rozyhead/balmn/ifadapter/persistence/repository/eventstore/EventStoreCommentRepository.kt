package com.github.rozyhead.balmn.ifadapter.persistence.repository.eventstore

import com.github.rozyhead.balmn.domain.model.board.comment.Comment
import com.github.rozyhead.balmn.domain.model.board.comment.CommentId
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.service.repository.CommentRepository
import com.github.rozyhead.balmn.util.ddd.Version

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