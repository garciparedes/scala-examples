
package com.garciparedes.sparkexamples.main

/**
  * Created by garciparedes on 08/05/2017.
  */


import com.garciparedes.sparkexamples.GraphUpgradeable
import org.apache.log4j.{Level, Logger}
import org.apache.spark.graphx._
import org.apache.spark.graphx.lib.PageRank
import org.apache.spark.{SparkConf, SparkContext}

object GraphUpgrapdeableSparkExample {
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.WARN)

    val conf = new SparkConf()
    conf.setAppName("GraphUpdateable Spark Example")
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)

    val edges = sc.parallelize(Array(
      Edge(1L, 2L, 1),
      Edge(2L, 3L, 1)
    ))

    val tuples = Array(
      (3L, 1L),
      (1L, 3L)
    )

    //var g = new GraphUpgradeable(sc)
    //g.addToGraph(edges)

    var g2 = Graph.fromEdges[Int, Int](
      edges, 1
    )
    //g2.edges.foreach(println)


    //g.addToGraph(tuples)
    //g.print()
    sc.stop()
  }
}
