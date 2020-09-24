package io.github.shalastra

import java.io.IOException

import zio.console._
import zio.{ExitCode, URIO, ZIO}

object Main extends App {

  def run(args: List[String]): URIO[Console, ExitCode] =
    myAppLogic.exitCode

  val myAppLogic: ZIO[Console, IOException, Unit] =
    for {
      _ <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _ <- putStrLn(s"Hello, ${name}, welcome to ZIO!")
    } yield ()
}
