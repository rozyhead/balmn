package com.github.rozyhead.balmn.board.application.repository

import com.github.rozyhead.balmn.board.domain.model.comment.CommentId
import com.github.rozyhead.balmn.common.domain.model.Version
import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent

/**
 * @author takeshi
 */
interface CommentRepository {

  fun save(commentId: CommentId, version: Version, vararg additionalEvents: CommentEvent)

}