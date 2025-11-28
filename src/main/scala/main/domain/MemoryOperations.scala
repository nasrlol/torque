package main.domain

import main.services._

import scala.util.hashing
import scala.util.hashing.MurmurHash3
import scala.math._
import scala.collection.immutable.ListSet
import scala.collection.mutable.ArrayBuffer

/**
 *
 * Large memory object to allocate on the heap 
 * This is an implementation by me 
 *
 * */

sealed Trait MemoryObject {
  

}

class MemoryAllocater {

  /*
   *
   * for stressing the memory we want to do some heavy heap allocations back and forth
   *
   * */

  def run(): Unit = println("stressing memory") 


  def moveToMemory(): Unit ={

  } 
} 



