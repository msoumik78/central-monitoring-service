package com.example.central_monitoring_service.service

import io.nats.client.Connection
import io.nats.client.Dispatcher
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets


@Service
class NATSSubscriber (private val connection: Connection) {

  @Value("\${job.nats.topic}")
  var topic: String? = null

  @PostConstruct
  fun subscriberTopic() {
    val dispatcher: Dispatcher = connection.createDispatcher()
    dispatcher.subscribe(topic) { message ->
      println("Message Received -> " + String(message.getData(), StandardCharsets.UTF_8))
    }
  }
}
