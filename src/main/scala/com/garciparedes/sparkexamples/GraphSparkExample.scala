
package com.garciparedes.sparkexamples

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.graphx.lib.PageRank
import org.apache.spark.{SparkConf, SparkContext}

object GraphSparkExample {
  def main(args: Array[String]): Unit = {
    val graphFile = "./datasets/wiki-Vote.txt"

    val conf = new SparkConf()
    conf.setAppName("Graph Spark Example")
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)

    val graph = GraphLoader.edgeListFile(sc, graphFile).persist()
    val ranks = PageRank.run(graph, 5).vertices.persist()

    ranks.join(graph.inDegrees)
      .takeOrdered(100)(Ordering[Double].reverse.on(_._2._1))
      .foreach(println)

    graph.edges.take(100).foreach(println)
    sc.stop()
  }
}
