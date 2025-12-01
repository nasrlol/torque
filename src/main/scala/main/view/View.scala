package main.view

import zio._
import main.infrastructure._
import main.services._
import java.lang.System
import oshi._

/**
 *
 * Handle the user interface together with the general user flow
 *
 **/

/**
 *
 * Trying to make the passed methods runnable by the fibers
 *
 **/

class View extends Runner {


  var resources: Resources = new Resources

  /**
   * General utils that will be used a lot later
   * */

  /**
   * ASCII code that clears terminal screen
   * source: https://stackoverflow.com/questions/56608263/how-to-clear-terminal-screen-in-scala 
  */

  def pressToContinue: Task[Unit] = Console.printLine("\nPress enter to continue") *> Console.readLine.unit

  /**
   * Multi line println for a nice UI 
   * putting "|" in front of the lines to strip the margin
   * source: https://docs.scala-lang.org/scala3/book/first-look-at-types.html#strings
   * */ 

  def header: Task[Unit] =  {

    Console.printLine(
      """
      _/  |_  ___________  ________ __   ____  
      \   __\/  _ \_  __ \/ ____/  |  \_/ __ \ 
      |  | (  <_> )  | \< <_|  |  |  /\  ___/ 
      |__|  \____/|__|   \__   |____/  \___  >
      |__|           \/ 

      |========== Welcome to Torque ==========|
      |    Cpu and Ram Stress testing tool    |
      |    in Scala                           |
      |    Author: Abdellah El Morabit        |
      |=======================================|
      """
  ) *> pressToContinue 
  }

  /**
   * Safely casting a string to an int
   * source: https://alvinalexander.com/scala/how-cast-string-to-int-in-scala-string-int-conversion/
   */
  def toInt(stringInput: String): Int = {
    try {
      stringInput.toInt
    } catch {
      // return 6 to exit the program
      case e: Exception => 6
    }
  }

  def resourcesView: ZIO[Any, Throwable, Unit] = {
    val sysInfo: SystemInfo = new SystemInfo 
    val hardware = sysInfo.getHardware
    val memory = hardware.getMemory
    val sensors = hardware.getSensors
    val cpu = hardware.getProcessor

    ZIO.attempt {
      Console.printLine("\u001b[2J")
      println(s"""
        ===================================================
          || CPU Load:        ${cpu.getSystemCpuLoad(1000) * 100}%
          || Logical Cores:   ${cpu.getLogicalProcessorCount()}
          || Physical Cores:  ${cpu.getPhysicalProcessorCount()}
          || Temperature:     ${sensors.getCpuTemperature()}°C
          || Total RAM:       ${memory.getTotal() / (1024.0 * 1024 * 1024)} GB
          || Available RAM:   ${memory.getAvailable() / (1024.0 * 1024 * 1024)} GB
          ===================================================
            """.stripMargin)
    } *> pressToContinue
  } 

  def menuDesign: Task[Unit] = {

    ZIO.attempt {
        Console.printLine("\u001b[2J")
        println(

      """
      _/  |_  ___________  ________ __   ____  
      \   __\/  _ \_  __ \/ ____/  |  \_/ __ \ 
      |  | (  <_> )  | \< <_|  |  |  /\  ___/ 
      |__|  \____/|__|   \__   |____/  \___ >
      |__|           \/ 

      |======= Select the stress test ========|
      | 1: light Cpu                          | 
      | 2: Heavy Cpu                          | 
      | 3: Light Ram                          | 
      | 4: Heavy Ram                          | 
      | 5: Http                               | 
      | 6: Exit                               | 
      |=======================================|

      """
    ) 
    }
  }

  def menu: ZIO[Any, Throwable, Unit] = {
    for {
      _ <- menuDesign
      input <- Console.readLine("Enter your choice: ")
      _ <- toInt(input) match {
        case 1 => lightCpuRun *> resourcesView *> menu
        case 2 => heavyCpuRun *> resourcesView *> menu
        case 3 => lightRamRun *> resourcesView *> menu
        case 4 => heavyRamRun *> resourcesView *> menu
        case 5 => httpHandler *> resourcesView *> menu
        case 6 => Console.printLine("Exiting...") *> ZIO.succeed(System.exit(0))
        case _ => Console.printLine("Invalid choice") *> pressToContinue *> menu
      }
    } yield ()
  }

def serveView: ZIO[Any, Throwable, Unit] =  header *> menu 


}

