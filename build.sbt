import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "uk.filippov",
      scalaVersion := "2.12.3",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.nrinaudo" %% "kantan.csv" % "0.3.1",
      "com.nrinaudo" %% "kantan.csv-java8" % "0.3.1",
      "com.nrinaudo" %% "kantan.csv-generic" % "0.3.1",
      "com.nrinaudo" %% "kantan.csv-java8" % "0.3.1"
    )
  )
