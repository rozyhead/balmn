package com.github.rozyhead.balmn.infrastructure.eventstore.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.rozyhead.balmn.infrastructure.eventstore.EventMessage
import com.github.rozyhead.balmn.infrastructure.eventstore.ReadProjection
import com.github.rozyhead.balmn.infrastructure.eventstore.jdbc.tables.EventMessages
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import rx.Emitter
import rx.Observable
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * @author takeshi
 */
class JdbcReadProjection(
    val objectMapper: ObjectMapper,
    val scheduledExecutorService: ScheduledExecutorService
) : ReadProjection {

  override fun allStreams(): Observable<EventMessage> {
    return Observable.fromEmitter<EventMessage>({ emitter ->
      var lastEventId = 0L
      val future = scheduledExecutorService.scheduleWithFixedDelay({
        if (emitter.requested() > 0L) {
          EventMessages.select { EventMessages.eventId greater lastEventId }
              .limit(emitter.requested().toInt()).forEach {
            val eventMessage = toEventMessage(it)
            lastEventId = eventMessage.eventId
            emitter.onNext(eventMessage)
          }
        }
      }, 0, 1, TimeUnit.SECONDS)
      emitter.setCancellation { future.cancel(true) }
    }, Emitter.BackpressureMode.BUFFER)
  }

  override fun currentAllStreams(): Observable<EventMessage> {
    return Observable.fromEmitter<EventMessage>({ emitter ->
      scheduledExecutorService.execute {
        EventMessages.selectAll().forEach {
          val eventMessage = toEventMessage(it)
          emitter.onNext(eventMessage)
        }
        emitter.onCompleted()
      }
    }, Emitter.BackpressureMode.BUFFER)
  }

  override fun streamById(streamId: String): Observable<EventMessage> {
    return Observable.fromEmitter<EventMessage>({ emitter ->
      var lastEventId = 0L
      val future = scheduledExecutorService.scheduleWithFixedDelay({
        if (emitter.requested() > 0L) {
          EventMessages.select {
            (EventMessages.streamId eq streamId) and
                (EventMessages.eventId greater lastEventId)
          }.limit(emitter.requested().toInt()).forEach {
            val eventMessage = toEventMessage(it)
            lastEventId = eventMessage.eventId
            emitter.onNext(eventMessage)
          }
        }
      }, 0, 1, TimeUnit.SECONDS)
      emitter.setCancellation { future.cancel(true) }
    }, Emitter.BackpressureMode.BUFFER)
  }

  override fun currentStreamById(streamId: String): Observable<EventMessage> {
    return Observable.fromEmitter<EventMessage>({ emitter ->
      scheduledExecutorService.execute {
        EventMessages.select { EventMessages.streamId eq streamId }.forEach {
          val eventMessage = toEventMessage(it)
          emitter.onNext(eventMessage)
        }
        emitter.onCompleted()
      }
    }, Emitter.BackpressureMode.BUFFER)
  }

  private fun toEventMessage(row: ResultRow): EventMessage {
    return EventMessage(
        eventId = row[EventMessages.eventId],
        streamId = row[EventMessages.streamId],
        version = row[EventMessages.streamVersion],
        eventType = row[EventMessages.eventType],
        payload = row[EventMessages.payload],
        createdAt = jodaTimeToJavaTime(row[EventMessages.createdAt])
    )
  }

}