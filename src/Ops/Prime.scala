package com.nsrddyn.ops
import com.nsrddyn.tools.Benchmark
import scala.util.hashing
import scala.util.hashing.MurmurHash3
import  com.nsrddyn.Traits.*
import scala.math._
import scala.collection.immutable.ListSet
import scala.collection.mutable.ArrayBuffer


class Prime()  {

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
    if n <= 1 then false
    else !(2 to math.sqrt(n).toInt).exists(i => n % i  == 0)


  }

  def run(n: Int, result: Boolean): Unit = {

    for i <- 0 to n do if isPrime(i) == result then println("true") else println("false") 
  }


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


class Hash {

  def run(word: String, loopSize: Int): Unit = {

    /* TODO: implement ALU friendly, so high speed hashing
     * to continuously loop over voor stressing 
     * ALU 
     *
     * While looking for hashing algorithmes to implement I stumbled on: 
     * https://scala-lang.org/api/3.x/scala/util/hashing/MurmurHash3$.html
     *
     * which is an implemntation of **smasher** http://github.com/aappleby/smhasher
     * the exact type of hashing algorithm I was looking for
     *
     * In the scala description they state: "This algorithm is designed to generate
     * well-distributed non-cryptographic hashes. It is designed to hash data in 32 bit chunks (ints). "
     * 
     * (ints) -> ALU
     *
     */ 

     for i <- 0 to loopSize do MurmurHash3.stringHash(word) 

  }
}

class CholeskyDecomposition {

  /*
   *  Floating point operation to stress the cpu
   *  Calculate the number of KFLOPS / FLOPS
   *  implementation of the Cholesky decomposition
   *  More information on the Cholesky decomposition at: 
   *  https://en.wikipedia.org/wiki/Cholesky_decomposition
   *  
   *  Linpack uses the cholesky decomposition
   *  https://www.netlib.org/linpack/
   *
   *  https://www.geeksforgeeks.org/dsa/cholesky-decomposition-matrix-decomposition/
   *
   *  The Cholesky decomposition maps matrix A into the product of A = L · LH where L is the lower triangular matrix and LH is the transposed,
   *  complex conjugate or Hermitian, and therefore of upper triangular form (Fig. 13.6).
   *  This is true because of the special case of A being a square, conjugate symmetric matrix. 
   */ 

  def run(matrix: Vector[Vector[Int]]): Unit = {

    val size: Int = matrix.size
    val lower: ArrayBuffer[ArrayBuffer[Int]] = ArrayBuffer[ArrayBuffer[Int]]()

    for 
      i <- 0 to size 
      j <- 0 until i 
    do 
      if i == j then lower(i)(j) = getSquaredSummation(lower, i, j, matrix) else lower(j)(j) = getReversedSummation(lower, i, j, matrix)

  }

  private def getReversedSummation(lower: ArrayBuffer[ArrayBuffer[Int]], i: Int, j: Int, matrix: Vector[Vector[Int]]) = {
    math.sqrt(matrix(j)(j) - (0 until j).map { k => lower(i)(k) * lower(j)(k) }.sum).toInt
  }
  private def getSquaredSummation(lower: ArrayBuffer[ArrayBuffer[Int]], i: Int, j: Int, matrix:  Vector[Vector[Int]]) = {
    ((matrix(i)(j) - (0 until j).map { k => math.pow(lower(j)(k), 2)}.sum) / lower(j)(j)).toInt
  }
}

