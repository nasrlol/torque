package main.infrastructure

import zio._
import zio.http._

/** 
 * HTTP Handler
 * using ZIO 
 *
 *
 *  source: https://ziohttp.com */

object Greeter extends ZIOAppDefault {
  val routes =
    Routes(
      Method.GET / "stability" -> handler { (req: Request) =>
        val name = req.queryOrElse("", "World")
        Response.text("stable!")
      }
    )

  def run = Server.serve(routes).provide(Server.default)
}
