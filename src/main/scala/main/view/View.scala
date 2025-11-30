package main.view

import zio._
import main.infrastructure._
import main.services._
import java.lang.System

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

  def clearScreen: Unit = print("\u001b[H\u001b[2J")

  def pressToContinue: Unit = {
    println("\nPress enter to continue") 
    scala.io.StdIn.readLine()
  }



  /**
   * Multi line println for a nice UI 
   * putting "|" in front of the lines to strip the margin
   * source: https://docs.scala-lang.org/scala3/book/first-look-at-types.html#strings
   * */ 

  def header: Unit =  {

    println(
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
    ) 
    pressToContinue
  }

  /**
   * Safely casting a string to an int
   * source: https://alvinalexander.com/scala/how-cast-string-to-int-in-scala-string-int-conversion/
   */
  def toInt(stringInput: String): Int = {
    try {
      stringInput.toInt
    } catch {
      // return 3 to exit the program
      case e: Exception => 3
    }
  }

  def menu: ZIO[Any, Throwable, Unit] = {

    var continue = true
    var target = ""

    while (continue) {

      Thread.sleep(500)
      clearScreen
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
        | 5: Exit                               | 
        |=======================================|
   
        """
      ) 
      target = scala.io.StdIn.readLine()
      continue = false
    }

    clearScreen
    toInt(target) match {

      case 1 => lightCpuRun <&> resources.getCpuInfo
      case 2 => heavyCpuRun <&> resources.getCpuInfo
      case 3 => lightCpuRun <&> resources.getRamInfo
      case 4 => heavyCpuRun <&> resources.getRamInfo


    }
  } 

  def serveView: ZIO[Any, Throwable, Unit] = {
    clearScreen
    header
    menu
  }
}
