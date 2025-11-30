/**
 *      __                                     
 *    _/  |_  ___________  ________ __   ____  
 *    \   __\/  _ \_  __ \/ ____/  |  \_/ __ \ 
 *     |  | (  <_> )  | \< <_|  |  |  /\  ___/ 
 *     |__|  \____/|__|   \__   |____/  \___  >
 *                           |__|           \/ 
 */

package main 

import main.domain._
import main.services._
import main.infrastructure._
import main.view._
import oshi._
import zio.http._
import java.lang.foreign._
import zio._


object Torque extends ZIOAppDefault with Runner  {



  def run: ZIO[Any, Throwable, Unit] = {

    handler()

    
    /**
     * In case the user needs a quick basic run without running through the DIY DUI
     * */

    //   args(1) match {
    //     case "cpu"  => lightCpuRun
    //     case "ram"  => lightRamRun 
    //     case _      => handler() 
    //   }

  }

  def serve = {
    Server.serve(routes)
      .provide(Server.default)
      .exitCode
  }

  /**
   *
   * Stress the given target
   * Capture the information
   * and send that over an API
   *
   * => doing these steps in a handler to be a little more modular
   * */

  def handler(): ZIO[Any, Throwable, Unit] = {

    val v: View = new View

    for {

      _ <- ZIO.debug("started") 
      _ <- v.serveView.fork
      _ <- ZIO.debug("finished")

    } yield () 
  }
}
