package com.example.central_monitoring_service.service

import io.nats.client.Connection
import io.nats.client.Dispatcher
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.logging.Level
import java.util.logging.Logger


@Service
class NATSSubscriber (private val connection: Connection) {

  var logger: Logger = Logger.getLogger(NATSSubscriber::class.java.getName())

  @Value("\${job.nats.temp-topic-name}")
  val natsTempTopicName: String? = null

  @Value("\${job.nats.humidity-topic-name}")
  val natsHumidityTopicName: String? = null

  @Value("\${threshold.temp}")
  val tempThreshold: Int? = null

  @Value("\${threshold.humidity}")
  val humidityThreshold: Int? = null

  @PostConstruct
  fun subscribeToTopic() {
    val dispatcher: Dispatcher = connection.createDispatcher()
    dispatcher.subscribe(natsTempTopicName) { message ->
      val tempData = String(message.getData(), StandardCharsets.UTF_8)
      if (tempData.toInt() > tempThreshold!!) {
        logger.log(Level.WARNING, "Temp is $tempData which is beyond threshold")
      }
    }
    dispatcher.subscribe(natsHumidityTopicName) { message ->
      val humidityData = String(message.getData(), StandardCharsets.UTF_8)
      if (humidityData.toInt() > tempThreshold!!) {
        logger.log(Level.WARNING, "Humidity is $humidityData % which is beyond threshold")
      }    }
  }
}
