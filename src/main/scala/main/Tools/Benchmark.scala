package main.Tools

import main.Ops.*
import main.Tests.*
import main.Tools.*
import oshi._
import java.time.Instant

class Benchmark {
  /* 
   * Calculate the time between the start of the execution of the function and the end
   * */
  def measureTime(work: => Unit): Long = {

    val start = System.nanoTime()
    work
    val end = System.nanoTime()
    end - start
  } 

  // TODO: map this to an actual precision value
  def measurePrecision(work: => Boolean, expectedResult: Boolean): Unit = if work == expectedResult then println(true) else println(false) 
}


class PrimeRunner {


  def run(threads: Int): Unit = {

    val pr = new Prime()
    val br = new Benchmark()

    /*
     * test cases
     *
     * 7919 true
     * 2147483647 false
     */

    val time = pr.run(7919, true)
    println(time)

  } 
}

