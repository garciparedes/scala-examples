name := "scala-examples"

version := "0.1"

scalaVersion := "2.11.11"

lazy val sparkVersion = "2.3.0"
lazy val spark = "org.apache.spark"

lazy val breezeVersion = "0.13.2"
lazy val scalaNlp = "org.scalanlp"


libraryDependencies  ++= Seq(

  // Scala Dependencies
  scalaNlp %% "breeze" % breezeVersion,
  scalaNlp %% "breeze-natives" % breezeVersion,
  scalaNlp %% "breeze-viz" % breezeVersion,

  // Spark Dependencies
  spark %% "spark-core" % sparkVersion,
  spark %% "spark-sql" % sparkVersion,
  spark %% "spark-streaming" % sparkVersion,
  spark %% "spark-graphx" % sparkVersion,
  spark %% "spark-mllib" % sparkVersion

)


resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"