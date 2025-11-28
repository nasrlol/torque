scalaVersion := "3.7.4"
version := "1.0"
name := "torque"

// multi threading dependency
libraryDependencies += "dev.zio" %% "zio" % "2.1.22"
// testing dependency
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
// % ipv %% omdat het een java library is ipv scala
// resources dependency 
libraryDependencies += "com.github.oshi" % "oshi-core" % "6.8.3"

// http dependency
libraryDependencies += "dev.zio" %% "zio-http" % "3.3.3"
