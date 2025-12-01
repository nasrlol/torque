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
import java.lang.foreign._
import zio._


object Torque extends ZIOAppDefault with Runner  {


  def run:  ZIO[Any, Throwable, Unit] =  { 

    val v: View = new View
    v.serveView
    httpHandler
  }


  /**
   * This method runs after the UI finishes which means that the stress tests also finished
   * Which allows us to post the gathered data to the API
   * */

}
