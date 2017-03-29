# Finatra Metrics - Graphite Example

By default, Finatra already provide metrics support through `finagle-stats`, in order to allow Finatra to "push" metrics to a time series database such as Graphite, OpenTSDB, etc, use [finagle-metrics](https://github.com/rlazoti/finagle-metrics) in any Finatra based project.

## Steps to integrate to Graphite:

1. Add dependency library on `build.sbt`.
2. Create/Copy GraphiteMetricsModule.scala into your project.
3. Reference **GraphiteMetricsModule** into your server definition.
4. Add all configuration prefixed with **metrics**.
5. Run the server !!!