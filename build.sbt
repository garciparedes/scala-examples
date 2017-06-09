name := "scala-examples"

version := "1.0"

val sparkVersion = "2.1.1"
val breezeVersion = "0.12"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core"       % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-graphx"     % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-streaming"  % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-mllib"      % sparkVersion
libraryDependencies += "org.scalanlp"     %% "breeze"           % breezeVersion
libraryDependencies += "org.scalanlp"     %% "breeze-natives"   % breezeVersion
libraryDependencies += "org.scalanlp"     %% "breeze-viz"       % breezeVersion

resolvers += "Sonatype Snapshots" at  "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Sonatype Releases"  at  "https://oss.sonatype.org/content/repositories/releases/"
