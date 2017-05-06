
package com.garciparedes.sparkexamples

import java.util.Calendar

import org.apache.spark.graphx._
import org.apache.spark.graphx.lib.PageRank
import org.apache.spark.{SparkConf, SparkContext}

object BigDynamicGraphSparkExample {
  def main(args: Array[String]): Unit = {
    val pagerankIters = 2
    val topKResults = 100

    val conf = new SparkConf()

    conf.setAppName("Dynamic Graph Spark Example")
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)
    val graphFile = "./datasets/wiki-Vote.txt"

    println(Calendar.getInstance().getTime + "\tStart!")

    var graph: Graph[Int, Int] = GraphLoader.edgeListFile(sc, graphFile)
    println(Calendar.getInstance().getTime + "\tDone Load!")

    var ranks = PageRank.run(graph, pagerankIters).vertices
    ranks.takeOrdered(topKResults)(Ordering[Double].reverse.on(_._2))
    //.foreach(println)

    println(Calendar.getInstance().getTime + "\tDone First PageRank!")


    graph = Graph(graph.vertices, graph.edges.union(
      sc.parallelize(Array(
        Edge(1L, 3L, 1)
      ))
    )).groupEdges((a, b) => a + b)

    println(Calendar.getInstance().getTime + "\tDone Update!")


    ranks = PageRank.run(graph, pagerankIters).vertices
    ranks.takeOrdered(topKResults)(Ordering[Double].reverse.on(_._2))
    //.foreach(println)

    println(Calendar.getInstance().getTime + "\tDone Second PageRank!")

    sc.stop()
    println(Calendar.getInstance().getTime + "\tFinish!")

  }
}
