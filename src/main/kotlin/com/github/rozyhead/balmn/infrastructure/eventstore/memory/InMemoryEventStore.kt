package com.github.rozyhead.balmn.infrastructure.eventstore.memory

import com.github.rozyhead.balmn.infrastructure.eventstore.EventMessage
import com.github.rozyhead.balmn.infrastructure.eventstore.EventStore
import com.github.rozyhead.balmn.infrastructure.eventstore.ReadProjection
import rx.Observable
import rx.lang.kotlin.PublishSubject
import rx.lang.kotlin.synchronized
import rx.lang.kotlin.toObservable
import java.time.Clock
import java.time.LocalDateTime
import java.util.concurrent.locks.ReentrantReadWriteLock

class InMemoryEventStore : EventStore, ReadProjection {

  private val lock = ReentrantReadWriteLock()
  private val readLock = lock.readLock()
  private val writeLock = lock.writeLock()

  private val eventMessages = mutableListOf<EventMessage>()
  private val subject = PublishSubject<EventMessage>().synchronized()

  override fun exists(streamId: String): Boolean = withReadLock {
    this.eventMessages.find { it.streamId == streamId } != null
  }

  override fun eventMessages(streamId: String): List<EventMessage>? = withReadLock {
    val events = this.eventMessages.filter { it.streamId == streamId }
    if (events.isNotEmpty()) events else null
  }

  override fun batchAppend(streamId: String, version: Long, events: List<Any>) {
    val savedMessages = withWriteLock {
      val oldMessages = this.eventMessages(streamId)
      require(oldMessages == null || oldMessages.last().version == version, { "Stream streamVersion conflicts" })

      var eventId = this.eventMessages.last().eventId
      var eventVersion = version
      val messages = events.map {
        EventMessage(++eventId, streamId, ++eventVersion, it, LocalDateTime.now())
      }

      this.eventMessages.addAll(messages)

      messages
    }

    savedMessages.forEach { this.subject.onNext(it) }
  }

  override fun allStreams(): Observable<EventMessage> = withReadLock {
    Observable.concat(currentAllStreams(), subject.asObservable())
  }

  override fun currentAllStreams(): Observable<EventMessage> = withReadLock {
    eventMessages.toObservable()
  }

  override fun streamById(streamId: String): Observable<EventMessage> = withReadLock {
    Observable.concat(currentStreamById(streamId), subject.asObservable())
  }

  override fun currentStreamById(streamId: String): Observable<EventMessage> = withReadLock {
    eventMessages.filter { it.streamId == streamId }.toObservable()
  }

  private fun <R> withReadLock(action: () -> R): R {
    readLock.lock()
    try {
      return action()
    } finally {
      readLock.unlock()
    }
  }

  private fun <R> withWriteLock(action: () -> R): R {
    writeLock.lock()
    try {
      return action()
    } finally {
      writeLock.unlock()
    }
  }

}