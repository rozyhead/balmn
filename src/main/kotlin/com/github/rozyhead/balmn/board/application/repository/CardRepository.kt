package com.github.rozyhead.balmn.board.application.repository

import com.github.rozyhead.balmn.board.domain.model.card.CardEvent
import com.github.rozyhead.balmn.board.domain.model.card.CardId
import com.github.rozyhead.balmn.common.domain.model.Version

/**
 * @author takeshi
 */
interface CardRepository {

  fun exists(cardId: CardId): Boolean

  fun save(cardId: CardId, version: Version, vararg additionalEvents: CardEvent)

}