package io.github.shalastra

import zio.{console, App, ZEnv, ZIO}
import zio.console.Console

object Main extends App {

  val program: ZIO[Console, Nothing, Unit] =
    console.putStrLn("TicTacToe game!")

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] =
    program.foldM(
      error =>
        console.putStrLn(s"Execution failed with: $error") *> ZIO.succeed(1),
      _ => ZIO.succeed(0)
    )
}
