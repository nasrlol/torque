package main.infrastructure

import zio._
import zio.http._
import main.domain._

/**
 *
 * API routes to show the results of a stress test
 * Routes => /cpu /ram /platform
 *
 * */
val routes = Routes (

  // the zio catch was needed for some reason 
  Method.GET / "cpu" -> handler{
    val resources = new Resources()
    resources.getCpuInfo.map(info => Response.text(info.toString))
    .catchAll(err => ZIO.succeed(Response.internalServerError("cpu route failed")))},
  Method.GET / "ram" -> handler{
    val resources = new Resources()
    resources.getRamInfo.map(info => Response.text(info.toString))
    .catchAll(err => ZIO.succeed(Response.internalServerError("ram route failed")))},
  Method.GET / "platform" -> handler{
    val resources = new Resources()
    resources.getPlatformInfo.map(info => Response.text(info.toString))
    .catchAll(err => ZIO.succeed(Response.internalServerError("platform route failed")))},
)

