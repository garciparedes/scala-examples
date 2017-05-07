
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
    val ssc = new StreamingContext(sc, Seconds(3))

    val lines = ssc.socketTextStream("localhost", 9999)

    val words = lines.flatMap(_.split("\n"))


    val lineString2TupleLong = { (line: String) => {
      val pattern = """^([0-9]+)[\t ]+([0-9]+)$""".r
      line match {
        case pattern(v1, v2) => (v1.toLong, v2.toLong)
        case _ => None
      }
    }
    }

    val g = new GraphUpgradeable(sc)


    lines.foreachRDD { rdd =>
      rdd.map(lineString2TupleLong)
        .collect { case x: (Long, Long) => x }
        .collect()
        .foreach { (record: (Long, Long)) =>
          g.addToGraph(record)
        }
      //g.print()
    }

    ssc.start()
    ssc.awaitTermination()

  }

  def addToGraph(edge: Any): Any = {
    println(edge)
  }

}
