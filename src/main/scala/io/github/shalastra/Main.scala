package io.github.shalastra

import zio._
import zio.blocking.Blocking
import zio.clock.Clock
import zio.console._
import zio.kafka.consumer.Consumer.{AutoOffsetStrategy, OffsetRetrieval}
import zio.kafka.consumer.{Consumer, ConsumerSettings, Subscription}
import zio.kafka.serde._


object Main extends App {

  def run(args: List[String]): URIO[ZEnv, ExitCode] =
    readKafkaPrg.exitCode


  val consumerSettings: ConsumerSettings = ConsumerSettings(List("localhost:9092"))
    .withGroupId("group")
    .withClientId("client")
    .withOffsetRetrieval(OffsetRetrieval.Auto(AutoOffsetStrategy.Latest))

  val subscription: Subscription = Subscription.topics("single-topic")

  val readKafka: RIO[Console with Blocking with Clock, Unit] =
    Consumer.consumeWith(consumerSettings, subscription, Serde.string, Serde.string) {
      case (key, value) =>
        putStrLn(s"Received message ${key}: ${value}")
      // Perform an effect with the received message
    }

  val readKafkaPrg: URIO[ZEnv, ExitCode] =
    readKafka.exitCode

}
