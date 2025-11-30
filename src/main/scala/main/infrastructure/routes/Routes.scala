package main.infrastructure


import zio._
import zio.http._

val routes = Routes (


  Method.GET / Root -> handler(Response.text("Greetings at your services")),
  Method.GET / "cpu" -> handler(Response.text("Greetings at your services")),
  Method.GET / "ram" -> handler(Response.text("Greetings at your services"))


)

