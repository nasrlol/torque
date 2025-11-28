package main.services

import main.domain._
import main.services._
import oshi._
import zio._


enum Status:
  case PASS
  case FAIL

class Stress(val status: Status) {


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

}

