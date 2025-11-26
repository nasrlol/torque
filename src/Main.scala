package com.nsrddyn

import com.nsrddyn.fpu.CholeskyDecomposition
import com.nsrddyn.Tests.CholeskyDecompositionTest
import java.time.Instant
import com.nsrddyn.alu.*
import com.nsrddyn.tools.Benchmark

enum Status:
  case PASS 
  case FAIL 


object Torque extends ZIOAppDefault {

  println("hello world")

  @main def main(args: String*): Unit =  { println("\u001b[2J\u001b[H")
    println("--- TORQUE STRESS TESTING UTILITY ---")

    var tester: CholeskyDecompositionTest = new CholeskyDecompositionTest
    println(tester.test())

  }

  var p: Prime = new Prime
  p.run()

}

