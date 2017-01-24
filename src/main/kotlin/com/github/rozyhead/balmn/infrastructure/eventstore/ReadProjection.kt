package com.github.rozyhead.balmn.infrastructure.eventstore

import com.github.rozyhead.balmn.util.ddd.DomainEvent
import rx.Observable

interface ReadProjection {

  fun allStreams(): Observable<DomainEvent>

  fun currentAllStreams(): Observable<DomainEvent>

  fun streamById(streamId: String): Observable<DomainEvent>

  fun currentStreamById(streamId: String): Observable<DomainEvent>

}