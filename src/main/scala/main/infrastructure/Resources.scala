package main.infrastructure

import main.domain._
import main.services._
import main.infrastructure._
import oshi._ 
import zio._

class Resources {

  /**
   * Creating instances for gathering the data 
   */
  private val sysInfo: SystemInfo = new SystemInfo 
  private val hardware = sysInfo.getHardware
  private val memory = hardware.getMemory
  private val sensors = hardware.getSensors
  private val cpu = hardware.getProcessor

  /**
   * Platform Mehtods
   * */
  def getPlatformInfo: Task[PlatformInfo] = {
    ZIO.attempt { 
      /**
       * Pass the OS version to the DTO to later transfer it over the API
       **/
      PlatformInfo ( currentPlatform = sysInfo.getOperatingSystem.getFamily)
    }
  }


  /**
   * CPU methods
   * */
  def getCpuInfo: Task[CpuInfo] = {
    ZIO.attempt {
      /*
       *  227 oshi/hardware/CentralProcessor.java
       *  method takes long value as delay
       * */

      CpuInfo (
        load = cpu.getSystemCpuLoad(1000) * 1000,
        temperature = sensors.getCpuTemperature(),
        cores = cpu.getLogicalProcessorCount(),
        physicalCores = cpu.getPhysicalProcessorCount() ,

        /**
         * I will propably call this method when i'm about to run 
         * a stress test so it's safe to set it to RUNNING
         * */
        status = Status.RUNNING
      )
    }
  }


  /**
   * Memory specific methods
   * */
  def getRamInfo: Task[RamInfo] = {

    ZIO.attempt { 

      val totalMemory = memory.getTotal()

      RamInfo (
        usage = totalMemory - memory.getAvailable().toDouble,
        total = totalMemory,
        status = Status.RUNNING
      )
    }
  }
}
