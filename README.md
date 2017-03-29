# Finatra Metrics - Graphite Example

By default, Finatra already provide metrics support through `finagle-stats`, in order to allow Finatra to "push" metrics to a time series database such as Graphite, OpenTSDB, etc, use [finagle-metrics](https://github.com/rlazoti/finagle-metrics) in any Finatra based project.

## Steps to integrate to Graphite:

1. Add dependency library on [build.sbt](https://github.com/amura2406/finatra-metrics-graphite/blob/master/build.sbt#L50-L51).
2. Create/Copy [GraphiteMetricsModule.scala](https://github.com/amura2406/finatra-metrics-graphite/blob/master/src/main/scala/com/github/amura/module/GraphiteMetricsModule.scala) into your project.
3. Reference **GraphiteMetricsModule** into your server definition, in this case [APIServer.scala](https://github.com/amura2406/finatra-metrics-graphite/blob/master/src/main/scala/com/github/amura/server/APIServer.scala#L25).
4. Add all configuration prefixed with [metrics](https://github.com/amura2406/finatra-metrics-graphite/blob/master/src/main/resources/conf/application.conf#L3-L9).
5. Run the server !!!