package main 

import Ops.*
import Tests.*

import java.time.Instant
import zio._


/*
 * *> = "Then" (think arrow pointing to what matters)
 * <&> = "Both" (symbol looks like things going in both directions)
 *  <* = "Keep left" (pointing to what you keep)
 *  &> = "Keep right" (pointing to what you keep)
 * */

object Torque extends ZIOAppDefault {

  def runCholeskyTest: ZIO[Any, Throwable, Unit] = 
    ZIO.attempt {
      val matrix: Vector[Vector[Int]] = Vector(Vector(1,2,3), Vector(1,2,3), Vector(1,2,3))
      val cd = new CholeskyDecomposition
      cd.run(matrix)
    }.catchAll { error => Console.printError(s"failed: $error")}

  def runPrimeTest: ZIO[Any, Throwable, Unit] = 
    ZIO.attempt {
      val p = new Prime 
      p.run(390483094, true)
    }.catchAll { error => Console.printError(s"failed: $error")}

  def runSequential: ZIO[Any, Throwable, Unit] =  
    Console.printLine("sequential test") *>
    runCholeskyTest *>
    runPrimeTest

  def runParallel: ZIO[Any, Throwable, Unit] = 
    Console.printLine("CholeskyTest") *> 
    (runCholeskyTest <&> runPrimeTest).unit

  override def run: ZIO[ZIOAppArgs & Scope, Any, Any] = runParallel 
}

