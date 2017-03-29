package com.github.amura.server

import com.github.amura.controller.TestController
import com.github.amura.module.{ConsoleMetricsModule, GraphiteMetricsModule, TypesafeConfigModule}
import com.google.inject.Module
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, ExceptionMappingFilter, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.inject.Logging

/**
  * Created by amura on 3/28/17.
  */
object APIServerMain extends APIServer

class APIServer extends HttpServer with Logging {
  protected override def defaultFinatraHttpPort: String = ":8090"

  protected override def failfastOnFlagsNotParsed: Boolean = true

  override protected def modules: Seq[Module] = Seq(
    TypesafeConfigModule,
//    ConsoleMetricsModule,
    GraphiteMetricsModule
  )

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .filter[ExceptionMappingFilter[Request]]

      .add[TestController]
  }
}
