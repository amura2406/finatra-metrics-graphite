package com.github.amura.module

import java.net.UnknownHostException

import scala.sys.process._
import java.util.concurrent.TimeUnit

import com.codahale.metrics.graphite.{Graphite, GraphiteReporter, PickledGraphite}
import com.google.inject.Provides
import com.twitter.finagle.metrics.MetricsStatsReceiver
import com.twitter.inject.{Injector, Logging, TwitterModule}
import com.typesafe.config.Config

/**
  * Created by amura on 3/28/17.
  */
object GraphiteMetricsModule extends TwitterModule with Logging{

  @Provides
  def graphite(config: Config) = {
    val host = config.getString("metrics.host")
    val port = config.getInt("metrics.port")

    new Graphite(host, port)
  }

  /**
    * Use PickledGraphite to send metrics in batches
    */
//  @Provides
//  def pickledGraphite(config: Config) = {
//    val host = config.getString("metrics.host")
//    val port = config.getInt("metrics.port")
//
//    new PickledGraphite(host, port)
//  }

  @Provides
  def reporter(graphite: Graphite, config: Config) = {
    val prefix = config.getString("metrics.prefix")
    val hostname = "hostname".!!.trim.replaceAll("\\.", "-")
    info(s"Detected hostname => $hostname")
    if(hostname.isEmpty)
      throw new UnknownHostException("exec command \"hostname\" doesn't yield anything")

    GraphiteReporter.forRegistry(MetricsStatsReceiver.metrics)
      .prefixedWith(s"$prefix.$hostname")
      .convertRatesTo(TimeUnit.SECONDS)
      .convertDurationsTo(TimeUnit.MILLISECONDS)
      .build(graphite)
  }

  override def singletonPostWarmupComplete(injector: Injector): Unit = {
    val config = injector.instance[Config]
    val reporter = injector.instance[GraphiteReporter]
    val interval = config.getInt("metrics.interval")

    reporter.start(interval, TimeUnit.SECONDS)
  }
}
