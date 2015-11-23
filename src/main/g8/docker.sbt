import com.typesafe.sbt.packager.docker._

maintainer:= "Your Name"

dockerExposedPorts in Docker := Seq(9000, 9443)

version in Docker := "latest"

dockerCommands += ExecCmd("CMD", "-J-javaagent:\${(defaultLinuxInstallLocation in Docker).value}/lib/org.aspectj.aspectjweaver-1.8.7.jar")
