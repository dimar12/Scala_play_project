lazy val main = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "play-webapp",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "9.4.1209",
      "com.typesafe.play" %% "play-slick" % "2.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
      "com.typesafe.slick" %% "slick" % "3.1.1",
      "com.h2database" % "h2" % "1.4.192",
      "org.webjars" % "bootstrap" % "3.3.7",
      "org.webjars" % "jquery" % "2.2.3",
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
    )
  )

