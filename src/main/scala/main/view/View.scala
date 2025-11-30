package main.view


/**
 *
 * Handle the user interface together with the general user flow
 *
 **/

class View {


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

  def checkInput(input: String): Unit =  {
    
     toInt(input) match {

       case 1 => println("stressing cpu")
       case 2 => println("stressing ram")
       case 3 => System.exit(0)
    
  }
}

  def menu: Unit = {

    var continue = true

    while (continue) {

      Thread.sleep(500)
      clearScreen
      println(

        """
       _/  |_  ___________  ________ __   ____  
       \   __\/  _ \_  __ \/ ____/  |  \_/ __ \ 
        |  | (  <_> )  | \< <_|  |  |  /\  ___/ 
        |__|  \____/|__|   \__   |____/  \___  >
                             |__|           \/ 
                             
        |======= Select the stress test ========|
        | 1: CPU                                | 
        | 2: Ram                                | 
        | 3: Exit                               | 
        |=======================================|
   
        """
      ) 
      
      checkInput(scala.io.StdIn.readLine())
     }

  }

  def serveView: Unit = {
    
    clearScreen
    header
    menu

  }
}
