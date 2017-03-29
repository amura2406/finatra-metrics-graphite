import sbt.Keys._

name := "finatra-metrics-graphite"

version := "1.0"

scalaVersion := "2.11.8"

fork in run := true
parallelExecution in Test := false

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo,
  "Twitter Maven" at "https://maven.twttr.com",
  "Finatra Repo" at "http://twitter.github.com/finatra",
  "jitpack" at "https://jitpack.io",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
  "Sonatype OSS Releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")

lazy val versions = new {
  val finatra             = "2.8.0"
  val bijections          = "0.9.5"
  val guice               = "4.0"
  val logback             = "1.1.+"
  val typesafeConfig      = "1.3.0"
  val ficus               = "1.2.6"
  val typesafeConfigGuice = "0.0.3"
  val metrics             = "3.1.2"
  val finagle_metrics     = "0.0.8"

  val junit = "4.12"
  val mockito = "1.9.5"
  val scalatest = "3.0.0"
  val specs2 = "2.4.17"
  val scalacheck = "1.13.4"
  val novocode = "0.11"
}

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",

  "com.twitter" %% "finatra-http" % versions.finatra,
  "com.twitter" %% "bijection-util" % versions.bijections,
  "ch.qos.logback" % "logback-classic" % versions.logback,
  "com.typesafe" % "config" % versions.typesafeConfig,
  "com.github.racc" % "typesafeconfig-guice" % versions.typesafeConfigGuice,
  "com.iheart" % "ficus_2.11" % versions.ficus,
  "com.github.rlazoti" %% "finagle-metrics" % versions.finagle_metrics,
  "io.dropwizard.metrics" % "metrics-graphite" % versions.metrics,

  "com.twitter" % "finatra-http_2.11" % versions.finatra % Test,
  "com.twitter" % "inject-server_2.11" % versions.finatra % Test,
  "com.twitter" % "inject-app_2.11" % versions.finatra % Test,
  "com.twitter" % "inject-core_2.11" % versions.finatra % Test,
  "com.twitter" % "inject-modules_2.11" % versions.finatra % Test,
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % Test,

  "com.twitter" % "finatra-http_2.11" % versions.finatra % Test classifier "tests",
  "com.twitter" % "inject-server_2.11" % versions.finatra % Test classifier "tests",
  "com.twitter" % "inject-app_2.11" % versions.finatra % Test classifier "tests",
  "com.twitter" % "inject-core_2.11" % versions.finatra % Test classifier "tests",
  "com.twitter" % "inject-modules_2.11" % versions.finatra % Test classifier "tests",

  "junit" % "junit" % versions.junit % "test",
  "org.mockito" % "mockito-core" % versions.mockito % Test,
  "org.scalatest" % "scalatest_2.11" % versions.scalatest % Test,
  "org.specs2" % "specs2_2.11" % versions.specs2 % Test,
  "org.scalacheck" %% "scalacheck" % versions.scalacheck % Test,
  "com.novocode" % "junit-interface" % versions.novocode % Test
)