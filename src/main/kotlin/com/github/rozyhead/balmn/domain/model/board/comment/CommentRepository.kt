package com.github.rozyhead.balmn.domain.model.board.comment

import com.github.rozyhead.balmn.domain.model.board.sheet.CommentEvent

/**
 * @author takeshi
 */
interface CommentRepository {

  fun save(commentId: CommentId, events: List<CommentEvent>, oldEvents: List<CommentEvent>)

}