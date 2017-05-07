
package com.garciparedes.sparkexamples

import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming._
import org.apache.spark.{SparkConf, SparkContext}

object StreamSparkExample {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val appName = "Edge Stream Graph Spark Example"
    val conf = new SparkConf()
    conf.setAppName(appName)
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Milliseconds(50))

    val lines = ssc.socketTextStream("localhost", 9999)


    val pattern = """^([0-9]+)[\t ]+([0-9]+)$""".r

    val lineString2TupleLong = {
      (line: String) => {
        line match {
          case pattern(v1, v2) => (v1.toLong, v2.toLong)
          case _ => None
        }
      }
    }

    val g = new GraphUpgradeable(sc)


    lines.foreachRDD { rdd =>
      g.addToGraph(
        rdd.map(lineString2TupleLong)
          .collect { case x: (Long, Long)@unchecked => x }
          .collect()
      )
      //g.print()
    }

    ssc.start()
    ssc.awaitTermination()

  }

}
