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

// TODO: program is exiting without completing the tests, my guess is it has something to do with ZIO Fiber forks
class StressCpu() extends Stress {


  def safety: Unit = {
    val systemInfo = new SystemInfo 
    val sensors =  systemInfo.getHardware.getSensors
    while (true) do if sensors.getCpuTemperature > 80 then println("overheat")
  }


  def runCholeskyTest: ZIO[Any, Throwable, Unit] = {
    val cd = new CholeskyDecomposition
    ZIO
      .attempt {
        val matrix: Vector[Vector[Int]] = Vector(Vector(32480934, 3240994, 20394402), Vector(324234, 2342354, 5432432), Vector(340983, 12038, 9834))
        cd.run(matrix)
      }
        .catchAll { error => Console.printError(s"failed: $error") }
  }

  def runPrimeTest: ZIO[Any, Throwable, Unit] = {
    val p = new Prime
    ZIO
      .attempt {
        p.run(390483094)
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

  private val memoryAllocater = new MemoryAllocater
  private val rand = new scala.util.Random 
  // TODO: set the status upon a pass or fail
  // private var status: Status = false 

  /**
   *
   * Idea of a run sequential for memory is allocating and deallocating a size 
   * lots of times back and forth
   * 
   * */

  override def runSequential: ZIO[Any, Throwable, Unit] = {

    val list: List[Int] = List(2030994, 493084, 43434, 43904 ,2103092, 230932)
    val offset: Int = 4

    ZIO.attempt {

      memoryAllocater.setValues(list, offset)
      memoryAllocater.deallocateMemory

    }.catchAll { error => Console.printError(s"failed: sequential memory error")}

  }

  override def runParallel: ZIO[Any, Throwable, Unit] = {

    /**
     * TODO: set the limit to the amount of CPU cores
     * I wanted to set affinities per core but not there yet
     * source: https://zio.dev/overview/basic-concurrency/
     * */
    ZIO.foreachPar(1 to 8) { 
      /** 
       * no need to save wich worked were on at the moment
       *so were throwing it away with _ 
      */ 
      _ => ZIO.attemptBlocking {

        val list = List.fill(6)(rand.nextInt(1000000) + 100000)
        /**
         * still setting an offset of 4 because 
         * we are filling then memory with 4 byte integers
         */
        val offset = 4

        memoryAllocater.setValues(list, offset)
        memoryAllocater.deallocateMemory


      }.catchAll { error => Console.printError(s"failed: $error") }
    }.unit
  }
}

trait Runner {

  private val c: StressCpu =  new StressCpu 
  private val r: StressRam =  new StressRam 

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
