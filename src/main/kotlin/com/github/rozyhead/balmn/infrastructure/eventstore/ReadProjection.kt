package com.github.rozyhead.balmn.infrastructure.eventstore

import rx.Observable

interface ReadProjection {

  fun allStreams(): Observable<EventMessage>

  fun currentAllStreams(): Observable<EventMessage>

  fun streamById(streamId: String): Observable<EventMessage>

  fun currentStreamById(streamId: String): Observable<EventMessage>

}