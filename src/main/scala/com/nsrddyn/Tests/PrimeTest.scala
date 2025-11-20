package com.nsrddyn.Test

import com.nsrddyn.alu.Prime
import com.nsrddyn.tools.Benchmark

class PrimeTest extends Prime {

  def runBasic(): Unit = {

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

  def runExtreme(): Unit = println("running some very have stuff!")
  


}
