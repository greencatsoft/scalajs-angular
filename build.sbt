lazy val scalaJsAngular = (project in file(".")).
  enablePlugins(ScalaJSPlugin).
  settings(
    name := "scalajs-angular",
    description := "AngularJS bindings for Scala.js.",
    organization := "com.greencatsoft",
    version := "0.7-SNAPSHOT",
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq("-feature","-deprecation"),
    homepage := Some(url("http://github.com/greencatsoft/scalajs-angular")),

    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.1",
      "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % "compile"
    ),

    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if(isSnapshot.value)
      	Some("snapshots" at nexus + "content/repositories/snapshots")
      else
      	Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },

    publishMavenStyle := true,

    pomIncludeRepository := { _ => false },

    pomExtra := (
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
        <developer>
          <id>mmx900</id>
          <name>Setzer Kim</name>
          <url>https://github.com/mmx900</url>
        </developer>
      </developers>
      <contributors>
        <contributor>
          <name>Jokade</name>
          <url>https://github.com/jokade</url>
        </contributor>
        <contributor>
          <name>Olivergg</name>
          <url>https://github.com/olivergg</url>
        </contributor>
        <contributor>
          <name>Reid Spencer</name>
          <url>https://github.com/reid-spencer</url>
        </contributor>
        <contributor>
          <name>Johannes Kastner</name>
          <url>https://github.com/jokaICS</url>
        </contributor>
        <contributor>
          <name>math85360</name>
          <url>https://github.com/math85360</url>
        </contributor>
        <contributor>
          <name>Erik LaBianca</name>
          <url>https://github.com/easel</url>
        </contributor>
        <contributor>
          <name>Johannes Kastner</name>
          <url>https://github.com/jokaICS</url>
        </contributor>
        <contributor>
          <name>Tomas Zeman</name>
          <url>https://github.com/tzeman77</url>
        </contributor>
      </contributors>
    )
  )
