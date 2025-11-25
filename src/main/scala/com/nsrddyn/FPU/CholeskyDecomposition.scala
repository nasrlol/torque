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

