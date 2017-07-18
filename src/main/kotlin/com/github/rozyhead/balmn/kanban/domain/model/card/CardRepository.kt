package com.github.rozyhead.balmn.kanban.domain.model.card

import com.github.rozyhead.balmn.common.domain.model.DomainRepository

interface CardRepository : DomainRepository<CardId, CardEvent, Card> {
}