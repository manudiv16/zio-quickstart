package com.examplehealthcheck

import zhttp.http._
import zio.test._
import zio.test.Assertion._
import com.example.healthcheck._

object HealthcheckSpec extends ZIOSpecDefault:
  def spec = suite("http")(
    suite("health check")(
      test("ok status") {
        val actual = Healthcheck.routes(Request(method = Method.GET, url = URL(!! / "health")))
        assertZIO(actual)(equalTo(Response(Status.NoContent, Headers.empty, HttpData.empty)))
      }
    )
  )