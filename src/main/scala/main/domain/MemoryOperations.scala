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



/**
 * Large memory object to allocate on the heap 
 * This is an implementation by me 
 * A method to do this bassically an Array with large bytes allocated to it
 * */

// class MemoryObject {
//
//   // TODO: create a large sized object
//
// }

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

object MemoryAllocater  {

   def run(): Unit = {

    val arena: Arena = Arena.ofConfined()  

    // I think this should be around one 1Gb of data
    val memorySegment = arena.allocate((10 * 1024 * 1024) * 100)
    // not memseg.setAtIndex like in the docs :(
    /**
     * adding an offset so that the next piece of memory wont be overlapping with the one we just assigned
     * */
    val offset: Int = 4
    for i <- 0 until 10 do memorySegment.set(ValueLayout.JAVA_INT, offset * i, i)
    for i <- 0 until 10 do println("Index : " + memorySegment.get(ValueLayout.JAVA_INT, offset * i))
    
   } 
}
