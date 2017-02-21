package com.github.rozyhead.balmn.kanban.application.repository

import com.github.rozyhead.balmn.common.application.repository.DomainRepository
import com.github.rozyhead.balmn.kanban.domain.model.card.Card
import com.github.rozyhead.balmn.kanban.domain.model.card.CardEvent
import com.github.rozyhead.balmn.kanban.domain.model.card.CardId

interface CardRepository : DomainRepository<CardId, CardEvent, Card> {
}