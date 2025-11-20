package com.nsrddyn

object Torque {

  import java.time.Instant
  import com.nsrddyn.alu.*
  import com.nsrddyn.tools.Benchmark

  @main def main(args: String*): Unit = 

    val pr = new Prime()
    val br = new Benchmark()

    // ANSI ESCAPE CODE: clear screen
    println("\u001b[2J\u001b[H")
    println("--- TORQUE STRESS TESTING UTILITY ---")

    // val value = 2147483647
    val value = 200000 

    val time = br.measure(pr.run(value))
    println(time)

}


