package com.github.rozyhead.balmn.domain.model.board.comment

import com.github.rozyhead.balmn.domain.model.board.card.CardIdentifier

data class Comment(
    val identifier: CommentIdentifier,
    val cardIdentifier: CardIdentifier
)