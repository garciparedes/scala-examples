
package com.garciparedes.sparkexamples.main

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
    sc.setLogLevel("WARN")

    val graph = GraphLoader.edgeListFile(sc, graphFile).persist()
    val ranks = PageRank.runUntilConvergence(graph, 0.01).vertices.persist()

    ranks
      .collect.sortBy(_._2).reverse
      .foreach(println)
    sc.stop()
  }
}
