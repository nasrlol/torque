package main 

import Ops.*
import Tests.*
import tools.*
import java.time.Instant

object Torque {

  @main
  def main(args: String*): Unit =  { println("\u001b[2J\u001b[H")
    println("--- TORQUE STRESS TESTING UTILITY ---")

    var cdt: CholeskyDecompositionTest = new CholeskyDecompositionTest
    // returns an out of bounds error
    // println(cdt.test())
    var p: Prime = new Prime
    p.run(1000000000, true)

  }
}

