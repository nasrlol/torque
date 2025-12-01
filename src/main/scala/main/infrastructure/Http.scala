package main.infrastructure

import zio._
import zio.http._
import main.domain._


/** 
 * HTTP Handler
 * using ZIO 
 *
 *
 *  source: https://ziohttp.com */

 def httpHandler: ZIO[Any, Throwable, Unit] = {
   ZIO.attempt {
     Server.serve(routes)
       .provide(Server.default)
       .exitCode

   }
 }
