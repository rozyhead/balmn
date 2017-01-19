package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.board.comment.CommentId
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent
import com.github.rozyhead.balmn.util.ddd.Version

/**
 * @author takeshi
 */
interface CommentRepository {

  fun save(commentId: CommentId, version: Version, vararg additionalEvents: CommentEvent)

}