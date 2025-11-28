package main.domain

import main.services._

import scala.util.hashing
import scala.util.hashing.MurmurHash3 
import scala.collection.immutable.ListSet
import scala.math._
import java.lang.foreign._
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout
import scala.util.Using

object Memory {


  /**
   * Large memory object to allocate on the heap 
   * This is an implementation by me 
   * A method to do this bassically an Array with large bytes allocated to it
   * */

  class MemoryObject {

    // TODO: create a large sized object

  }

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

  class MemoryAllocater(arena: Arena)  {


    def run(): Unit = moveToMemory()

    def moveToMemory(): Unit = {

      val memseg = arena.allocate(10 * 4)
      // not memseg.setAtIndex like in the docs :(
      for i <- 0 to 10 do memseg.set(ValueLayout.JAVA_INT, i, i)

    } 

  } 
}
