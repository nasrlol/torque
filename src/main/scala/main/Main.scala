package main 

import Ops.*
import Tests.*
import oshi._
import java.time.Instant
import zio._


/*
 * *> = "Then" (think arrow pointing to what matters)
 * <&> = "Both" (symbol looks like things going in both directions)
 *  <* = "Keep left" (pointing to what you keep)
 *  &> = "Keep right" (pointing to what you keep)
 * */

object Torque extends ZIOAppDefault {

  // system resources
  val si: SystemInfo = new SystemInfo 
  val sensors = si.getHardware.getSensors
  val cpu = si.getHardware.getProcessor

  // stress operations 
  val p = new Prime 
  val cd = new CholeskyDecomposition

  def failSafe: Unit = while (true) do if sensors.getCpuTemperature > 80 then println("overheat")

  def runCholeskyTest: ZIO[Any, Throwable, Unit] = {

    ZIO.attempt {
      //TODO: declare a randomized array  to pass into the runner
      // cd.run(matrix)
    }.catchAll { error => Console.printError(s"failed: $error")}

  }

  def runPrimeTest: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt {
      // p.run(390483094, true)
    }.catchAll { error => Console.printError(s"failed: $error")}
  }

  def runSequential: ZIO[Any, Throwable, Unit] = {

    Console.printLine("sequential test") *>
    runCholeskyTest *>
    runPrimeTest
  }

  def runParallel: ZIO[Any, Throwable, Unit] = {

    Console.printLine("CholeskyTest") *>
    (runCholeskyTest <&> runPrimeTest).unit
  }

  def getPlatform: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt { 
      println(si.getHardware)
    }.catchAll { error => Console.printError(s"failed :$error")}
  }

  def getCpuFrequency: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt {
      /*
       *  227 oshi/hardware/CentralProcessor.java
       *  method takes long value as delay
       * */
      println("load: " + cpu.getSystemCpuLoad(1000) * 1000)
      println("logical cores: " + cpu.getLogicalProcessorCount())
      println("cores: " + cpu.getPhysicalProcessorCount())
      println("temperature: " + sensors.getCpuTemperature())
    }
  }

  def run: ZIO[ZIOAppArgs & Scope, Any, Any] = {
    Console.printLine("=== TORQUE STRESS TEST ===") *> runParallel <*> getPlatform  <*> getCpuFrequency
  }
}
