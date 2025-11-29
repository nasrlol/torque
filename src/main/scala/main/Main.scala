package main 

import main.domain._
import main.services._
import main.infrastructure._
import oshi._
// import zio._
import java.lang.foreign._


object Torque /* extends ZIOAppDefault */ {

  def main(args: Array[String]): Unit = MemoryAllocater.run() 

  // override def run: ZIO[ZIOAppArgs & Scope, Any, Any] = memoryExecution 
  //
  // def memoryExecution: ZIO[Any, Throwable, Unit] = {
  //
  //
  //   for {
  //     _ <- ZIO.debug("started") 
  //     f1 = MemoryAllocater.run() 
  //     _ <- ZIO.debug("finished")
  //
  //   } yield () 
  // }
}
