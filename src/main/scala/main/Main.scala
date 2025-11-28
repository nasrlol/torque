package main 

import main.Ops.*
import main.Tests.*
import main.Tools.*
import main.Runner.*
import oshi._
import zio._


/*
 * *> = "Then" (think arrow pointing to what matters)
 * <&> = "Both" (symbol looks like things going in both directions)
 *  <* = "Keep left" (pointing to what you keep)
 *  &> = "Keep right" (pointing to what you keep)
 * */

object Torque extends ZIOAppDefault {

  def run: ZIO[ZIOAppArgs & Scope, Any, Any] = {
    Console.printLine("=== TORQUE STRESS TEST ===") 
    
  }

}
