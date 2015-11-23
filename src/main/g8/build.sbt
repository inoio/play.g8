name := "$name$"

organization := "$organization;format="normalize"$"

scalaVersion := "$scala_version$"

version in ThisBuild := "$version$"

val kamonDependencies = Seq(
  "io.kamon" %% "kamon-play-24" % "$kamon_version$",
  "io.kamon" %% "kamon-system-metrics" % "$kamon_version$",
  "io.kamon" %% "kamon-statsd" % "$kamon_version$",
  "org.aspectj" % "aspectjweaver" % "1.8.7"
)

val scaldiDependencies = Seq(
  "org.scaldi" %% "scaldi-play" % "$scaldi_version$"
)

val scalazDependencies = Seq(
  "org.scalaz" %% "scalaz-core" % "$scalaz_version$"
)

val playDependencies = Seq(
  ws,
  specs2 % Test
)

libraryDependencies ++=
  playDependencies ++
  kamonDependencies ++
  scaldiDependencies ++
  scalazDependencies ++ Seq(
)

lazy val root = (project in file(".")).
  enablePlugins(BuildInfoPlugin, JavaAppPackaging, PlayScala).
  settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "$package$"
  )
