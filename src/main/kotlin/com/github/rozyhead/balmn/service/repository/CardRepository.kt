package com.github.rozyhead.balmn.service.repository

import com.github.rozyhead.balmn.domain.model.board.card.CardEvent
import com.github.rozyhead.balmn.domain.model.board.card.CardId

/**
 * @author takeshi
 */
interface CardRepository {

  fun exists(cardId: CardId): Boolean

  fun save(cardId: CardId, events: List<CardEvent>, oldEvents: List<CardEvent>)

}