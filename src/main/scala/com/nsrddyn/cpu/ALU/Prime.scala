package com.nsrddyn.alu


import com.nsrddyn.tools.Benchmark

class Prime() extends Benchmark:  

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


  /*
  * TODO: I did the countrary of what i wanted to accieve with the is prime function
  *       We want the function to be less optimized so that the CPU has more work == more stress 
  */


  def isPrime(n: Int): Boolean = {
    val start = measure()
    if n <= 1 then false
    else !(2 to math.sqrt(n).toInt).exists(i => n % i  == 0)
  }

  def run(n: Int): Unit = for i <- 0 to n do isPrime(i)


  // TODO: implement measure methode to measure the time that it takes to find that prime number
  def measure(): Long ={

    val start = System.nanoTime()
    System.nanoTime()
    val end = System.nanoTime()
    start - end
  } 

