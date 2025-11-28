package main.domain

import main.services._

import scala.util.hashing
import scala.util.hashing.MurmurHash3 import scala.math._
import scala.collection.immutable.ListSet
import java.nio.ByteBuffer

/**
 *
 * Large memory object to allocate on the heap 
 * This is an implementation by me 
 * A method to do this bassically an Array with large bytes allocated to it
 *
 * */


class MemoryObject {

  object size {
    val size: Int = 10 * 1024 * 1024
  }

  val buffer: Vector[size.type] = Vector(1, 1) 
}



/*
 * Using java.nio.ByteBuffer
 * Allocate memory then free memory
 * for stressing the memory we want to do some heavy heap allocations back and forth
 * 
 *
 * */

class MemoryAllocater extends ByteBuffer {

  def run(): Unit = println("stressing memory") 


  def moveToMemory(): Unit ={

  } 
} 



