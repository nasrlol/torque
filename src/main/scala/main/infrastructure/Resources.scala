package main.infrastructure

import main.domain._
import main.services._
import main.infrastructure._
import oshi._ 
import zio._

class Resources {

  /**
   *
   * Creating instances for gathering the data 
   *
   */
  private val sysInfo: SystemInfo = new SystemInfo 
  private val hardware = sysInfo.getHardware
  private val memory = hardware.getMemory
  private val sensors = hardware.getSensors
  private val cpu = hardware.getProcessor


  /**
   *
   * Platform Mehtods
   * */

  def getPlatform: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt { 
      println(sysInfo.getHardware)
    }.catchAll { error => Console.printError(s"failed :$error")}
  }


  /**
   *
   * CPU methods
   *
   * */
  def getCpuInfo: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt {
      /*
       *  227 oshi/hardware/CentralProcessor.java
       *  method takes long value as delay
       * */
      println("load: " + cpu.getSystemCpuLoad(1000) * 1000)
      println("logical cores: " + cpu.getLogicalProcessorCount())
      println("cores: " + cpu.getPhysicalProcessorCount())
      println("temperature: " + sensors.getCpuTemperature())


      // TODO: assign the information in here to a method
    }
  }


  /**
   *
   * Memory specific methods
   *
   * */

  def getRamInfo: ZIO[Any, Throwable, Unit] = {

    ZIO.attempt { 

      memory.getTotal()
      val totalMemory = memory.getTotal()
      println("total memory" +totalMemory)
    }.catchAll { error => Console.printError(s"failed: $error")}
  }
}

// TODO: add method for viewing threads
