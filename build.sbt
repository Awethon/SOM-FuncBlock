name := "diploma2"

version := "0.1"

scalaVersion := "2.12.8"

val mavenLocal = "Local Maven Repository" at "file:///c:/Users/mi/.m2/repository"

resolvers += Resolver.mavenLocal

libraryDependencies += "dxelopes4students" % "Clustering" % "unspecified"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.7"
libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.1.7" % Test

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.5.21",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.21" % Test
)

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.21" % Test

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
