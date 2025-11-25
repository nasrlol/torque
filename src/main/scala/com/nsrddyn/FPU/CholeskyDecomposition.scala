package com.nsrddyn.fpu

import scala.math._
import scala.collection.immutable.ListSet
import scala.collection.mutable.ArrayBuffer

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

  def run(matrix: List[List[Int]]): Unit = {

    val n: Int = matrix.size

    // store the lower triangular matrix
    val lower = Vector[Vector[Int]]()


    for (i <- 0 until n)
    {
      for (j <- 0 until i)
        var sum: Double = 0

        if j == i then
        sum += math.pow(lowerBuffer(i)(j), 2)

        end if 
        lower(i)(j) = (sqrt(matrix(i)(j))())

        j += 1
    }
    i += 1
  }
  
  def (matrix: Vector[Vector[Int]], index: int, jindex: int ): Int = if j == 1 then return math.pow(matrix(index)(jindex)) else 


}
