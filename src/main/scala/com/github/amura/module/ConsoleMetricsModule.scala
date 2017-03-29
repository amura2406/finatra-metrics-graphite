package com.github.amura.module

import java.util.concurrent.TimeUnit

import com.codahale.metrics.ConsoleReporter
import com.twitter.finagle.metrics.MetricsStatsReceiver
import com.twitter.inject.{Injector, Logging, TwitterModule}
import com.typesafe.config.Config

/**
  * Created by amura on 3/28/17.
  */
object ConsoleMetricsModule extends TwitterModule with Logging{

  lazy val reporter = ConsoleReporter
    .forRegistry(MetricsStatsReceiver.metrics)
    .convertRatesTo(TimeUnit.SECONDS)
    .convertDurationsTo(TimeUnit.MILLISECONDS)
    .build

  override def singletonPostWarmupComplete(injector: Injector): Unit = {
    val config = injector.instance[Config]
    val interval = config.getInt("metrics.interval")
    reporter.start(interval, TimeUnit.SECONDS)
  }
}
