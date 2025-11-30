package main.infrastructure

import main.domain._
import main.services._
import main.infrastructure._
import oshi._ 
import zio._

class Resources {

  // Top levle instance 
  val sysInfo: SystemInfo = new SystemInfo 

  val hardware = SysInfo.getHardware()
  val sensors = SysInfo.getHardware.getSensors
  val cpu = SysInfo.getHardware.getProcessor

  def failSafe: Unit = while (true) do if sensors.getCpuTemperature > 80 then println("overheat")

  def getPlatform: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt { 
      println(SysInfo.getHardware)
    }.catchAll { error => Console.printError(s"failed :$error")}
  }


  def getCpuFrequency: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt {
      /*
       *  227 oshi/hardware/CentralProcessor.java
       *  method takes long value as delay
       * */
      println("load: " + cpu.getSystemCpuLoad(1000) * 1000)
      println("logical cores: " + cpu.getLogicalProcessorCount())
      println("cores: " + cpu.getPhysicalProcessorCount())
      println("temperature: " + sensors.getCpuTemperature())
    }
  }


  def getMemoryUsage: ZIO[Any, Throwable, Unit] = {
      

  }

  // TODO: add method for viewing threads
}
