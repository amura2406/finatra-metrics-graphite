package com.github.amura.controller

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.exceptions.{NotFoundException, ServiceUnavailableException}
import com.twitter.util.Future

import scala.util.Random

/**
  * Created by amura on 3/28/17.
  */
class TestController extends Controller {

  get("/v1/hello") { request: Request =>
    Future.value(
      Random.nextInt(100) match {
        case i:Int if i < 10 => throw NotFoundException()
        case i:Int if i < 30 => throw ServiceUnavailableException()
        case _ => "Hello"
      }
    )
  }
}
