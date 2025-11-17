package com.nsrddyn



class Prime(threads: Int) {


  /*
   * Calculate all primes up to limit
   * This should stress the ALU in someway,
   * doing this in a predictable manner, 
   * will hopefully keep the cpu pipeline busy
   * and that way stress the branch predictor
   *
   * math.sqrt(n) => a prime number has 2 factors, one of the factors 
   * of the prime numbers has to be smaller then n 
   * after that we check if the number is whole number and thereby checking if its a prime
   *
   */

  def isPrime(n: Int): Boolean = {
    if n <= 1 then false
    else !(2 to math.sqrt(n).toInt).exists(i => n % i  == 0)
  }

  def run(n: Int): Unit = for i <- 0 to n do isPrime(i)
}

