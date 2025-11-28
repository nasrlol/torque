import zio._
import zio.http._

// https://ziohttp.com/

object GreetingServer extends ZIOAppDefault {
  val routes =
    Routes(
      Method.GET / "stability" -> handler { (req: Request) =>
        val name = req.queryOrElse("", "World")
        Response.text("stable!")
      }
    )

  def run = Server.serve(routes).provide(Server.default)
}
