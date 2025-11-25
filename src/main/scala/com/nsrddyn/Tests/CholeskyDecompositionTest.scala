package com.nsrddyn.Tests

import com.nsrddyn.fpu.CholeskyDecomposition
import scala.collection.immutable.ListSet

class CholeskyDecompositionTest extends CholeskyDecomposition {

  def test(): Unit = {

    val cdp: CholeskyDecomposition = new  CholeskyDecomposition
    val matrix: List[List[Int]] = List.empty[List[Int]]

    println(cdp.run(matrix))

  }
}
