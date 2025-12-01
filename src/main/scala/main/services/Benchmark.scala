package main.services

import main.domain._
import java.time.Instant
import oshi._
import zio._

class Benchmark {

  private val sysInfo: SystemInfo = new SystemInfo 
  private val hardware = sysInfo.getHardware
  private val memory = hardware.getMemory
  private val sensors = hardware.getSensors
  private val cpu = hardware.getProcessor

  // TODO: map this to an actual precision value based on how accurate the stress actions have been executed
  def measurePrecision(work: => Boolean, expectedResult: Boolean): Unit = if work == expectedResult then println(true) else println(false) 

  /**
   *
   * When CPU's can't handle theyre current clock they often drop 
   * clock speed
   * This runs a check to see if it's stable in that aspect
   * */

  def compareCpuFrequencyToMax: Boolean =  {

    var result: Boolean = true 
    val coreFreqs = cpu.getCurrentFreq
    for i <- 0 to cpu.getLogicalProcessorCount do
    if coreFreqs(i).equals(null) then 
      if cpu.getMaxFreq() > coreFreqs(i) then false

    result
  }

  def safety: ZIO[Unit, Throwable, Unit] = {
    val systemInfo = new SystemInfo 
    val sensors =  systemInfo.getHardware.getSensors
    ZIO.attempt {
      while (true) do 
      if sensors.getCpuTemperature > 80 then println("overheat")
    }.catchAll { error => Console.printError(s"failed: $error") }
  }
}
