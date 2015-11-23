import com.typesafe.sbt.packager.docker._

maintainer:= "Your Name"

dockerExposedPorts in Docker := Seq(9000, 9443)

// uncomment this if you want docker images which default to
// your sbt version
version in Docker := "latest"

// add aspectj from the libs directory - defaults to /opt/docker/lib
dockerCommands += ExecCmd("CMD", "-J-javaagent:/opt/docker/lib/org.aspectj.aspectjweaver-1.8.7.jar")
