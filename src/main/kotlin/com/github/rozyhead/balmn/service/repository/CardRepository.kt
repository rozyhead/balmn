package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.board.card.CardEvent
import com.github.rozyhead.balmn.domain.model.board.card.CardId
import com.github.rozyhead.balmn.util.ddd.Version

/**
 * @author takeshi
 */
interface CardRepository {

  fun exists(cardId: CardId): Boolean

  fun save(cardId: CardId, version: Version, vararg additionalEvents: CardEvent)

}