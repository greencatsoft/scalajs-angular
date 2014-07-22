scalaJSSettings

name := "scalajs-angular"

organization := "com.greencatsoft"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

homepage := Some(url("http://github.com/greencatsoft/scalajs-angular"))

libraryDependencies ++= Seq(
  "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6")

