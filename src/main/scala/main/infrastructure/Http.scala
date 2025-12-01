package main.infrastructure

import zio._
import zio.http._
import main.domain._

/** 
 * HTTP Handler
 * using ZIO 
 *
 * This hander serves as the main HTTP server for the application.
 * It shows the performance  metrics at the time it gets run
 * and exposes them via HTTP endpoints.
 * You can check it out at http://localhost:8080/cpu or /ram or /platform
 *  source: https://ziohttp.com
 */

def httpHandler: ZIO[Any, Throwable, Unit] =  { 
  for { 
    h <- Server.serve(routes).provide(Server.defaultWithPort(8080)).exitCode.fork
    _ <- h.join
  } yield ()
}

// was returning an exit code previously but that caused a type mismatch
// [error]    |                  Too many type arguments for zio.UIO[A]
// [error]    |                  expected: [A]
// [error]    |                  actual:   [Any, Throwable]







