package com.nsrddyn.tools

class Benchmark {

  // add a reference to a function
  def measure(work: => Unit): Long = {

    val start = System.nanoTime()
    work
    val end = System.nanoTime()
    end - start
  } 

}
