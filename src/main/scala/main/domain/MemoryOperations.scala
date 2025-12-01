package main.domain

import main.services._
import scala.util.hashing
import scala.util.hashing.MurmurHash3
import scala.collection.immutable.ListSet
import scala.math._
import java.lang.foreign._
import scala.util.Using

/*
 * Using java.nio.ByteBuffer
 * Allocate memory then free memory
 * for stressing the memory we want to do some heavy heap allocations back and forth
 * --------- 
 *  not anymore
 *  switched to java.lang.foreign
 *
 * https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/lang/foreign/package-summary.html
 * */

case class Memory (arena: Arena, memorySegment: MemorySegment)


class MemoryAllocater {


  private var arena: Arena = _
  private var memorySegment: MemorySegment = _

  /**
   * adding an offset so that the next piece of memory wont be overlapping with the one we just assigned
   * */

  def createMemorySegment: Memory = Memory ( arena = Arena.ofConfined(), memorySegment = arena.allocate(1024 * 1024 * 1024))

  def setValues(values: List[Int], baseOffset: Int): Unit = {

    val stride = Integer.BYTES.toLong
    val start  = baseOffset.toLong

    for (i <- values.indices) {
      val addr = start + (i * stride)
      memorySegment.set(ValueLayout.JAVA_INT, addr, values(i))
    }

  }

  def getValues(offset: Int, len: Int): List[Int] = {

    val stride = Integer.BYTES.toLong
    val start  = offset.toLong
    (0 until len).map { i =>
      val addr = start + (i * stride)
      memorySegment.get(ValueLayout.JAVA_INT, addr)
    }.toList
  }

  def deallocateMemory: Unit = if arena != null then arena.close()

  def run(): Unit =  println("address: " + memorySegment.address())

}
