
package com.garciparedes.sparkexamples.main

import java.util.Calendar

import org.apache.spark.graphx._
import org.apache.spark.{SparkConf, SparkContext}

object BigDynamicGraphSparkExample {
  def main(args: Array[String]): Unit = {
    val topKResults = 100

    val conf = new SparkConf()

    conf.setAppName("Dynamic Graph Spark Example")
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)
    val graphFile = "./datasets/wiki-Vote.txt"

    println(Calendar.getInstance().getTime + "\tStart!")

    var graph: Graph[Int, Int] = GraphLoader.edgeListFile(sc, graphFile)
    println(Calendar.getInstance().getTime + "\tDone Load!")

    graph = Graph(graph.vertices, graph.edges.union(
      sc.parallelize(Array(
        Edge(1L, 3L, 1)
      ))
    )).groupEdges((a, b) => a + b)

    println(Calendar.getInstance().getTime + "\tDone Update!")

    sc.stop()
    println(Calendar.getInstance().getTime + "\tFinish!")

  }
}
