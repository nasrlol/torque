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

  private val address: String = null 
  private val arena: Arena = Arena.global()
  // TODO: get user to define the size to allocate
  private val memorySegment = arena.allocate(1024 * 1024 * 1024)

  /**
   * adding an offset so that the next piece of memory wont be overlapping with the one we just assigned
   * */

  def setValues(values: List[Int], offset: Int): Unit = for i <- values.indices do memorySegment.set(ValueLayout.JAVA_INT, offset * i, values(i))

  def getValues(offset: Int): Unit = (0 until 10).map(i => memorySegment.get(ValueLayout.JAVA_INT, offset * i))
  def deallocateMemory: Unit = arena.close()

  def run(): Unit =  println("address: " + memorySegment.address()) 

}
