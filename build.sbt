scalaJSSettings

name := "scalajs-angular"

organization := "com.greencatsoft"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-feature","-deprecation")

homepage := Some(url("http://github.com/greencatsoft/scalajs-angular"))

libraryDependencies ++= Seq(
  "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if(isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

pomIncludeRepository := { _ => false }

pomExtra := (
  <description>AngularJS bindings for Scala.js.</description>
  <url>http://github.com/greencatsoft/scalajs-angular</url>
  <licenses>
    <license>
      <name>Apache License 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:greencatsoft/scalajs-angular.git</url>
    <connection>scm:git:git@github.com:greencatsoft/scalajs-angular.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mysticfall</id>
      <name>Xavier Cho</name>
      <url>http://github.com/mysticfall</url>
    </developer>
  </developers>
)
