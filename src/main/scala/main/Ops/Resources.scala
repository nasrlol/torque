package main.Resources

import main.Ops.*
import main.Tests.*
import main.Tools.*
import oshi._ 
import zio._

class Resources {

  val si: SystemInfo = new SystemInfo 
  val sensors = si.getHardware.getSensors
  val cpu = si.getHardware.getProcessor

  def failSafe: Unit = while (true) do if sensors.getCpuTemperature > 80 then println("overheat")

  def getPlatform: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt { 
      println(si.getHardware)
    }.catchAll { error => Console.printError(s"failed :$error")}
  }


  def getCpuFrequency: ZIO[Any, Throwable, Unit] = {
    ZIO.attempt {
      /*
       *  227 oshi/hardware/CentralProcessor.java
       *  method takes long value as delay
       * */
      // println("load: " + cpu.getSystemCpuLoad(1000) * 1000)
      // println("logical cores: " + cpu.getLogicalProcessorCount())
      // println("cores: " + cpu.getPhysicalProcessorCount())
      // println("temperature: " + sensors.getCpuTemperature())
    }
  }
}
