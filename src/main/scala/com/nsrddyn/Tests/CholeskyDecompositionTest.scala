package com.nsrddyn.Tests

import com.nsrddyn.fpu.CholeskyDecomposition
import scala.collection.immutable.ListSet

class CholeskyDecompositionTest extends CholeskyDecomposition {

  def test(): Unit = {

    val cdp: CholeskyDecomposition = new  CholeskyDecomposition
    val matrix: Vector[Vector[Int]] = Vector(Vector(1,2,3),Vector(1,2,3),Vector(1,2,3))

    println(cdp.run(matrix))

  }



}
