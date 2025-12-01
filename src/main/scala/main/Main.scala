/**
 *      __                                     
 *    _/  |_  ___________  ________ __   ____  
 *    \   __\/  _ \_  __ \/ ____/  |  \_/ __ \ 
 *     |  | (  <_> )  | \< <_|  |  |  /\  ___/ 
 *     |__|  \____/|__|   \__   |____/  \___  >
 *                           |__|           \/ 
 *
 *
 * author: Abdellah El Morabit
 * year: 2025
 * course: Java Advanced
 */

package main 

import main.domain._
import main.services._
import main.infrastructure._
import main.view._
import oshi._
import java.lang.foreign._
import zio._


object Torque extends ZIOAppDefault with Runner {

  /**
   * The http handler runs after the UI finishes which means that the stress tests also finished
   * Which allows us to post the gathered data to the API
   * */

  def run: ZIO[Any, Throwable, Unit] = new View().serveView 

}
