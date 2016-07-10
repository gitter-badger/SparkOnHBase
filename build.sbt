
val sparkVersion = "1.5.0-cdh5.5.2"
val hbaseVersion = "1.0.0-cdh5.5.2"
val hadoopScope = Provided

lazy val commonSettings = Seq(
  scalaVersion := "2.10.5",
  scalacOptions += "-target:jvm-1.7",
  javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),
  crossPaths := false,
  publishMavenStyle := true,
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.6" %  Test,
    "org.scalacheck" %% "scalacheck" % "1.13.1" % Test,
    "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % Test,
    "org.mockito" % "mockito-core" % "1.10.19" % Test
  )
)

lazy val sparkDependencies =
  libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion % hadoopScope,
    "org.apache.spark" %% "spark-streaming" % sparkVersion % hadoopScope,

    "org.apache.spark" %% "spark-streaming" % sparkVersion % Test classifier "tests"
  )

lazy val hbaseDependencies =
  libraryDependencies ++= Seq(
    "org.apache.hbase" % "hbase-common" % hbaseVersion % hadoopScope,
    "org.apache.hbase" % "hbase-client" % hbaseVersion % hadoopScope,
    "org.apache.hbase" % "hbase-server" % hbaseVersion % hadoopScope,
    "org.apache.hbase" % "hbase-protocol" % hbaseVersion % hadoopScope,
    "org.apache.hbase" % "hbase-hadoop2-compat" % hbaseVersion % hadoopScope,
    "org.apache.hbase" % "hbase-hadoop-compat" % hbaseVersion % hadoopScope,

    "org.apache.hbase" % "hbase-client" % hbaseVersion % Test classifier "tests",
    "org.apache.hbase" % "hbase-server" % hbaseVersion % Test classifier "tests",
    "org.apache.hbase" % "hbase-hadoop2-compat" % hbaseVersion % Test classifier "tests",
    "org.apache.hbase" % "hbase-common" % hbaseVersion % Test classifier "tests",
    "org.apache.hbase" % "hbase-hadoop-compat" % hbaseVersion % Test classifier "tests"
  )

lazy val spark_on_hbase = project.
  in(file(".")).
  settings(
    commonSettings,
    autoScalaLibrary := false,
    version := "0.9.0",
    organization := "uk.co.faydark",
    name := "spark-on-hbase",
    description := "Spark context for HBase",
    licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")),

    sparkDependencies,
    hbaseDependencies,
    resolvers += "Cloudera Repos" at "https://repository.cloudera.com/artifactory/cloudera-repos"
  )
