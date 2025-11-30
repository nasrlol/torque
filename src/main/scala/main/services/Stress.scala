package main.services

import main.domain._
import main.services._
import main.infrastructure._
import oshi._
import zio._

abstract class Stress {

  def runSequential: ZIO[Any, Throwable, Unit]
  def runParallel: ZIO[Any, Throwable, Unit]

}

class StressCpu() extends Stress {


  def safety: Unit = {
    val systemInfo = new SystemInfo 
    val sensors =  systemInfo.getHardware.getSensors
    while (true) do if sensors.getCpuTemperature > 80 then println("overheat")
  }


  def runCholeskyTest: ZIO[Any, Throwable, Unit] = {
    ZIO
      .attempt {
        // TODO: declare a randomized array  to pass into the runner
        // cd.run(matrix)
      }
        .catchAll { error => Console.printError(s"failed: $error") }
  }

  def runPrimeTest: ZIO[Any, Throwable, Unit] = {
    ZIO
      .attempt {
        // p.run(390483094, true)
      }
        .catchAll { error => Console.printError(s"failed: $error") }
  }

  override def runSequential: ZIO[Any, Throwable, Unit] = {

    Console.printLine("sequential test") *>
    runCholeskyTest *>
    runPrimeTest
  }

  override def runParallel: ZIO[Any, Throwable, Unit] = {

    Console.printLine("CholeskyTest") *>
    (runCholeskyTest <&> runPrimeTest).unit
  }

}

class StressRam() extends Stress {


  override def runSequential: ZIO[Any, Throwable, Unit] = {

    ZIO
      .attempt {
        // TODO:
      }
        .catchAll { error => Console.printError(s"failed: $error") }

  }

  override def runParallel: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt {}.catchAll { error => Console.printError(s"failed: $error") }
  }
}

trait Runner {

  val c: StressCpu =  new StressCpu 
  val r: StressRam =  new StressRam 

  def heavyCpuRun: Task[Unit] = {

    for {
      _ <- c.runParallel
      _ <- c.runSequential 
      _ <- c.runParallel
    } yield()
  }

  def lightCpuRun: Task[Unit] = {

    for { 

      _ <- c.runSequential

    } yield()
  } 

  def heavyRamRun: Task[Unit] = {

    for {
      _ <- r.runParallel
      _ <- r.runSequential 
      _ <- r.runParallel
    } yield()
  }


  def lightRamRun: Task[Unit] = {

    for { 

      _ <- r.runSequential

    } yield()
  } 
}
