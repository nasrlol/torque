package main.infrastructure

import zio._
import zio.http._
import main.domain._

/**
 *
 * API routes to show the results of a stress test
 *
 * */

val routes = Routes (

  Method.GET / Root -> handler(Response.text("Greetings at your services")),
  Method.GET / "cpu" -> handler(Response.text("Greetings at your services")),
  Method.GET / "ram" -> handler(Response.text("Greetings at your services")),
  Method.GET / "platform" -> handler(Response.text("Greetings at your services"))

)

