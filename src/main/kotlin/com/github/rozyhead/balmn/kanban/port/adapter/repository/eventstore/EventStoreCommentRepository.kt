package com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore

import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.kanban.application.repository.CommentRepository
import com.github.rozyhead.balmn.kanban.domain.model.comment.Comment
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentEvent
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentId

class EventStoreCommentRepository(eventStore: EventStore) : CommentRepository {

  val helper = EventStoreRepositoryHelper<CommentEvent, Comment, CommentId>(
      eventStore = eventStore,
      eventClass = CommentEvent::class,
      emptyEntity = Comment(),
      streamIdOf = { "Comment-${it.uuid}" }
  )

  override fun exists(id: CommentId): Boolean
      = helper.existsInStore(id)

  override fun find(id: CommentId): Pair<Comment, Version>?
      = helper.findByStore(id)

  override fun save(id: CommentId, version: Version, vararg additionalEvents: CommentEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}