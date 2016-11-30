package com.github.rozyhead.balmn.domain.model.board.card

/**
 * @author takeshi
 */
interface CardRepository {

  fun exists(cardIdentifier: CardIdentifier): Boolean

  fun save(cardIdentifier: CardIdentifier, events: List<CardEvent>, oldEvents: List<CardEvent>)

}