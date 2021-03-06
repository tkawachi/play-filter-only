package com.github.tkawachi

import akka.stream.Materializer
import org.scalatest.funsuite.AnyFunSuite
import play.api.http.HttpFilters
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.streams.Accumulator
import play.api.mvc._
import play.api.test.FakeRequest
import play.api.test.Helpers._

import javax.inject.Inject
import scala.concurrent.Future

class PlayFilterOnlyTest extends AnyFunSuite {

  private val app = new GuiceApplicationBuilder()
    .routes({
      case ("GET", "/ok")    => TestActions.ok
      case ("GET", "/error") => TestActions.success
    })
    .build()

  test("Verify that the filter is set only for the specified path") {
    running(app) {
      route(app, FakeRequest("GET", "/ok"))

      val e = intercept[RuntimeException] {
        route(app, FakeRequest("GET", "/error"))
      }
      assert(e.getMessage === "Error!")
    }
  }
}

object TestActions {
  val ok: EssentialAction = (_: RequestHeader) => Accumulator.done(Results.Ok)
  val success: EssentialAction = (_: RequestHeader) => Accumulator.done(Results.Ok)
}

class TestFilter @Inject() (errorFilter: ErrorFilter) extends HttpFilters {

  import com.github.tkawachi.PlayFilterOnly._

  override def filters: Seq[EssentialFilter] = Seq(
    errorFilter.only(header => header.path == "/error")
  )
}

class ErrorFilter @Inject() (implicit val mat: Materializer) extends Filter {

  override def apply(f: RequestHeader => Future[Result])(rh: RequestHeader): Future[Result] =
    sys.error("Error!")

}

