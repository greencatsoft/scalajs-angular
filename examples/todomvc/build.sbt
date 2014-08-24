name := "todomvc-scalajs"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-feature","-deprecation")

scalaJSSettings

libraryDependencies += "com.greencatsoft" %%% "scalajs-angular" % "0.1-SNAPSHOT"

ScalaJSKeys.jsDependencies += ProvidedJS / "base.js"

skip in ScalaJSKeys.packageJSDependencies := false
