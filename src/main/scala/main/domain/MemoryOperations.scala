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





class MemoryAllocater {


  private var arena: Arena = _
  private var memorySegment: MemorySegment = _

  /**
   * adding an offset so that the next piece of memory wont be overlapping with the one we just assigned
   * */

  /**
   * Creation of arena and memory segment within the arena
   * if an arena already exists the method closes it and creates a new one
   * */

  def createMemorySegment: Unit = {
    if arena == null || memorySegment == null then {

      /**
       * close the current arena to assign a new one
       * trying to do this allot to stress the ram
       *
       * */
      
      if arena != null then arena.close()

      /**
       *
       * Assigning a global arena so multiple threads can access it
       * for example when a thread tries to close the previous arena
       *
       * */
      arena  = Arena.global()
      memorySegment = arena.allocate(1024L * 1024 * 1024) // 1 GiB

    }

  }

  /**
   * Checks if arena is created if it isn't call the createMemorySegment method
   * Close the previous arena if there is one and initlizate values on a memory segment
   * */
  def setValues(values: List[Int], baseOffset: Int): Unit = {
    if this.arena == null then createMemorySegment

    val stride = Integer.BYTES.toLong
    val start  = baseOffset.toLong

    for (i <- values.indices) {
      val addr = start + (i * stride)
      memorySegment.set(ValueLayout.JAVA_INT, addr, values(i))
    }

  }

  /**
   * Retrieves the set values that were initialized on
   * the off heap in the setValues method
   * */
  def getValues(offset: Int, len: Int): List[Int] = {

    val stride = Integer.BYTES.toLong
    val start  = offset.toLong
    (0 until len).map { i =>
      val addr = start + (i * stride)
      memorySegment.get(ValueLayout.JAVA_INT, addr)
    }.toList
  }


  def run(): Unit =  println("address: " + memorySegment.address())

}
