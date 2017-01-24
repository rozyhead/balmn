package com.github.rozyhead.balmn.infrastructure.eventstore.memory

import com.github.rozyhead.balmn.infrastructure.eventstore.ReadProjection
import com.github.rozyhead.balmn.util.ddd.DomainEvent
import rx.Observable
import rx.lang.kotlin.PublishSubject
import rx.lang.kotlin.emptyObservable
import rx.lang.kotlin.synchronized
import rx.lang.kotlin.toObservable

class InMemoryReadProjection(
    val inMemoryEventStore: InMemoryEventStore
) : ReadProjection {

  override fun allStreams(): Observable<DomainEvent> {
    val current = currentAllStreams()
    val subject = PublishSubject<DomainEvent>().synchronized()
    // TODO Unsubscribe on client unsubscribed
    @Suppress("UNUSED_VARIABLE")
    val unsubscribe = inMemoryEventStore.subscribe { streamId, event -> subject.onNext(event) }
    return Observable.concat(current, subject.asObservable())
  }

  override fun currentAllStreams(): Observable<DomainEvent>
      = inMemoryEventStore.events.toObservable().map { it.second }

  override fun streamById(streamId: String): Observable<DomainEvent> {
    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun currentStreamById(streamId: String): Observable<DomainEvent>
      = inMemoryEventStore.events(streamId)?.toObservable() ?: emptyObservable()

}