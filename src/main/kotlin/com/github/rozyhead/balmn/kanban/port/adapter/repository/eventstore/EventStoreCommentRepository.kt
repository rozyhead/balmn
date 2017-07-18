package com.github.rozyhead.balmn.kanban.port.adapter.repository.eventstore

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.msemys.esjc.EventStore
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.common.port.adapter.repository.eventstore.EventStoreRepositoryHelper
import com.github.rozyhead.balmn.kanban.domain.model.comment.Comment
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentEvent
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentId
import com.github.rozyhead.balmn.kanban.domain.model.comment.CommentRepository

class EventStoreCommentRepository(
    eventStore: EventStore,
    objectMapper: ObjectMapper
) : CommentRepository {

  val helper = EventStoreRepositoryHelper<CommentEvent, Comment, CommentId>(
      eventStore = eventStore,
      objectMapper = objectMapper,
      emptyEntity = Comment(),
      streamIdOf = { "Comment-${it.uuid}" }
  )

  override fun find(id: CommentId): Pair<Comment, Version>?
      = helper.findByStore(id)

  override fun save(id: CommentId, version: Version, vararg additionalEvents: CommentEvent)
      = helper.saveToStore(id, version, *additionalEvents)

}