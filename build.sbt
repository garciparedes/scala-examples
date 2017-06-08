name := "scala-examples"

version := "1.0"

val sparkVersion = "2.1.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-graphx" % sparkVersion
libraryDependencies += "org.apache.spark" % "spark-streaming" % sparkVersion
libraryDependencies += "org.apache.spark" % "spark-mllib" % sparkVersion
