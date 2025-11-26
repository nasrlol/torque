package com.nsrddyn.tools

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
