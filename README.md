## play.g8

An all in one  [giter8](https://github.com/n8han/giter8) template for [Play](https://www.playframework.com/).

*Monitor you application from the start*

### Features

 * supports Play 2.4 (via [play](http://www.playframework.com))
 * uses Scaldi for dependency injection (via [scaldi](http://scaldi.org))
 * provides built-in Kamon support (via [kamon](http://www.kamon.io))
 * provides built-in Docker support (via [sbt native packager](http://www.scala-sbt.org/sbt-native-packager/index.html))
 * integrated sbt-release plugin (without maven publishing via [sbt-release](https://github.com/sbt/sbt-release))
 * integrated sbt-buildinfo plugin (via [sbt-buildinfo](https://github.com/sbt/sbt-buildinfo))
 * .gitignore file



### No frills usage

Install giter8 following the instructions at https://github.com/n8han/giter8

```bash
g8 inoio/play
```

Answer the questions (enter to choose defaults):

```
A template to setup a play project with tons of helpers.

name [My Something Project]: banking
organization [inoio GmbH]: All Clear Banking
package [io.ino]: com.banking
version [0.0.1]:
sbt_version [0.13.8]:
play_version [2.4.4]:
kamon_version [0.5.2]:
scala_version [2.11.7]:
scaldi_version [0.5.11]:
scalaz_version [7.1.5]:

Template applied in ./banking

cd banking
sbt run
```

Open http://localhost:9000/status

### Docker/Kamon usage

You need to have `docker` and (optionally) `docker-compose` installed.

```bash
cd banking
sbt docker:publishLocal
cd docker
docker-compose up -d
docker_grafana_1 is up-to-date
Creating docker_banking_1
~/Documents/inoio/tmp/banking/docker$ docker-compose ps
      Name                    Command               State                                    Ports                                  
-----------------------------------------------------------------------------------------------------------------------------------
docker_banking_1   bin/banking -J-javaagent:/ ...   Up      0.0.0.0:32779->9000/tcp                                                 
docker_grafana_1   /usr/bin/supervisord             Up      0.0.0.0:32775->80/tcp, 0.0.0.0:32770->8125/udp, 0.0.0.0:32774->8126/tcp
~/Documents/inoio/tmp/banking/docker$
```

The application is now running on port *32779* and grafana on port *32775*. Passing aspectjweaver to your app has been taken care of. Kamon is configured to use the play, systems metrics and statsd modules.

Relevant sections for configuration are in `docker.sbt` and `application.conf` in the Kamon section. Before publishing docker containers to public repositories, please adjust the maintainer entry in `docker.sbt`

### Making releases

Release your project to git with
```
sbt Release
```
For details see [sbt-release](https://github.com/sbt/sbt-release).
The release pipeline is configured in `release.sbt`

### Customization

The main `build.sbt` is fairly modular:

```
name := "banking"

organization := "all-clear-banking"

scalaVersion := "2.11.7"

version in ThisBuild := "0.0.1"

val kamonDependencies = Seq(
  "io.kamon" %% "kamon-play-24" % "0.5.2",
  "io.kamon" %% "kamon-system-metrics" % "0.5.2",
  "io.kamon" %% "kamon-statsd" % "0.5.2",
  "org.aspectj" % "aspectjweaver" % "1.8.7"
)

val scaldiDependencies = Seq(
  "org.scaldi" %% "scaldi-play" % "0.5.11"
)

val scalazDependencies = Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.5"
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
    buildInfoPackage := "com.banking"
  )
```

and should be easy enough to adopt to your specific project needs.

Special aspects, like the configuration for sbt-release and docker are in their own dedicated sbt files.

*Please* remember to adjust the `play.crypto.secret = "changeme"` entry in the application.conf before going live.

### Improvements

If you have suggestions, bug reports etc. please file issues or pull requests.
