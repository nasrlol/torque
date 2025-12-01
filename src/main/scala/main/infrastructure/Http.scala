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

 def httpHandler(cpu: CpuInfo, ram: RamInfo, platform: PlatformInfo): Unit = {

   Server.serve(routes)
     .provide(Server.default)
     .exitCode

 }
