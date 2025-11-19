package com.nsrddyn

object Torque {

  import java.time.Instant
  import com.nsrddyn.alu.*

  @main def main(args: String*): Unit = 

  // ANSI ESCAPE CODE: clear screen
  println("\u001b[2J\u001b[H")
  println("--- TORQUE STRESS TESTING UTILITY ---")

  val now: Instant = Instant.now()
  println(now)

  val pr = new Prime()

  /*
  val intMax = 2147483647
  pr.run(intMax)
  */

  val intMax = 2147483647
  println(pr.measure())

}
