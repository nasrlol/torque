package main.services

import main.domain._
import main.services._
import oshi._
import zio._


enum Status:
  case PASS
  case FAIL

abstract class Stress {

  def runSequential: ZIO[Any, Throwable, Unit]
  def runParallel: ZIO[Any, Throwable, Unit]
  def setStatus: Unit

}

class StressCpu() extends Stress {

  var status: Status = Status.PASS

  def setStatus: Unit = status = Status.PASS 

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

  var status: Status = Status.PASS

  override def setStatus: Unit = status = Status.PASS 

  override def runSequential: ZIO[Any, Throwable, Unit] = {
    
    ZIO.attempt {
      // TODO: 
    }.catchAll { error => Console.printError(s"failed: $error")}

  }

  override def runParallel : ZIO[Any, Throwable, Unit] = {
    ZIO.attempt {

    }.catchAll { error => Console.printError(s"failed: $error")}
  }
}
