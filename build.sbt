name := """gotandamb-slack-invite"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test
libraryDependencies += "com.h2database"         %  "h2"                 % "1.4.194"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core"          % "0.13.1"
libraryDependencies += "net.databinder.dispatch" %% "dispatch-json4s-native" % "0.13.1"
