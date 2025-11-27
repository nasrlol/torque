package main.Tests

import scala.collection.immutable.ListSet
import zio._
import main.Ops.CholeskyDecomposition


class CholeskyDecompositionTest  {

  def test(): Unit = {

    val cdp: CholeskyDecomposition = new  CholeskyDecomposition
    val matrix: Vector[Vector[Int]] = Vector(Vector(1,2,3),Vector(1,2,3),Vector(1,2,3))

    println(cdp.run(matrix))

  }
}
