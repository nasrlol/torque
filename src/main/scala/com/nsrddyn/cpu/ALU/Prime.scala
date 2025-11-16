package com.nsrddyn



class Prime(threads: Int) {

  /*
   * Calculate all primes up to limit
   * This should stress the ALU in someway,
   * doing this in a predictable manner, 
   * will hopefully keep the cpu pipeline busy
   * and that way stress the branch predictor
   *
   * */


  // TODO: bad implementation of scala, scala prefers functional programming which  something I am not doing here

  def run(n: Long): Unit = {


    var iterator = 0

    // TODO: run the isPrime checks

  }

  def isPrime(n: Int): Boolean = {

    for 
      i <- 2 to 5 
      if isWholeNumber(n % i) == true then 
        true
    
    false

    // TODO: calculate  if the number is a prime number
    // TODO: fix errors
  }

  def isWholeNumber(n: Int | Float): Boolean = {
    // TODO: calculate if the number is a whole number
  }


}
