
name := "pingpong"

organization := "au.com.carringbushsw"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.4.17"

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.12
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1"

libraryDependencies += "au.com.carringbushsw" %% "tag" % "1.0-SNAPSHOT"