package com.github.rozyhead.balmn.domain.model.board.card

/**
 * @author takeshi
 */
interface CardRepository {

  fun exists(cardId: CardId): Boolean

  fun save(cardId: CardId, events: List<CardEvent>, oldEvents: List<CardEvent>)

}