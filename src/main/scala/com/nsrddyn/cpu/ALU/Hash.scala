package com.nsrddyn

import scala.util.hashing

class Hash {

import scala.util.hashing.MurmurHash3

  def run(word: String, loopSize: Int): Unit = {

    /* TODO: implement ALU friendly, so high speed hashing
     * to continuously loop over voor stressing 
     * ALU 
     *
     * While looking for hashing algorithmes to implement I stumbled on: 
     * https://scala-lang.org/api/3.x/scala/util/hashing/MurmurHash3$.html
     *
     * which is an implemntation of **smasher** http://github.com/aappleby/smhasher
     * the exact type of hashing algorithm I was looking for
     *
     * In the scala description they state: "This algorithm is designed to generate
     * well-distributed non-cryptographic hashes. It is designed to hash data in 32 bit chunks (ints). "
     * 
     * (ints) -> ALU
     *
     */ 

     for i <- 0 to loopSize do MurmurHash3.stringHash(word) 

  }
}
