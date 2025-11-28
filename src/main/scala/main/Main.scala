package main 

import main.domain._
import main.services._
import main.infrastructure._
import oshi._
import zio._


object Torque extends ZIOAppDefault {

  def run: ZIO[ZIOAppArgs & Scope, Any, Any] = {
    Console.printLine("=== TORQUE STRESS TEST ===") 
    
  }

}
