scalaVersion := "2.13.3"

name := "clik"
organization := "io.github.shalastra"
version := "1.0"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "1.0.1",
  "dev.zio" %% "zio-streams" % "1.0.0",
  "dev.zio" %% "zio-kafka" % "0.12.0"
)
