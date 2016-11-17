package com.github.rozyhead.balmn.domain.model.board.card

/**
 * @author takeshi
 */
interface CardRepository {

  fun save(cardIdentifier: CardIdentifier, events: List<CardEvent>, oldEvents: List<CardEvent>)

}