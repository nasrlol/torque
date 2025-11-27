scalaVersion := "3.7.4"
version := "1.0"
name := "torque"

libraryDependencies += "dev.zio" %% "zio" % "2.1.22"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
// % ipv %% omdat het een java library is ipv scala
libraryDependencies += "com.github.oshi" % "oshi-core" % "6.8.3"

