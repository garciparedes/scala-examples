
package com.garciparedes.sparkexamples.main

import org.apache.spark.graphx._
import org.apache.spark.graphx.lib.PageRank
import org.apache.spark.{SparkConf, SparkContext}

object DynamicGraphSparkExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()

    conf.setAppName("Dynamic Graph Spark Example")
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)

    val edges = sc.parallelize(Array(
      Edge(1L, 2L, 1.0),
      Edge(2L, 3L, 1.0)
    ))

    val vertices = sc.parallelize(Array(
      (1L, "Bob"),
      (2L, "Tom"),
      (3L, "Jerry")
    ))

    var graph = Graph(vertices, edges)
    var ranks = PageRank.run(graph, 5).vertices
    ranks.takeOrdered(100)(Ordering[Double].reverse.on(_._2))
      .foreach(println)


    graph = Graph(graph.vertices, graph.edges.union(
      sc.parallelize(Array(
        Edge(2L, 3L, 1.0),
        Edge(1L, 3L, 1.0)
      ))
    )).groupEdges((a, b) => a + b)

    ranks = PageRank.run(graph, 5).vertices
    ranks.takeOrdered(100)(Ordering[Double].reverse.on(_._2))
      .foreach(println)


    sc.stop()
  }
}
