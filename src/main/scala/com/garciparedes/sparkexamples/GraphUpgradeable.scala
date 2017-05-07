package com.garciparedes.sparkexamples

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{Edge, Graph}

/**
  * Created by garciparedes on 07/05/2017.
  */
class GraphUpgradeable(var sc: SparkContext, var graph: Graph[Int, Double]) extends Serializable {

  def this(sc: SparkContext) {
    this(sc, null)
  }

  def addToGraph(edge: (Long, Long)): Any = {
    println(edge)
    if (graph != null) {
      graph = Graph.fromEdges(graph.edges.union(sc.parallelize(Array(Edge(edge._1, edge._2, 1.0)))), 1)
    } else {
      graph = Graph.fromEdges(sc.parallelize(Array(Edge(edge._1, edge._2, 1.0))), 1)
    }
  }

  def print(edges: Int = 100): Unit = {
    if (graph != null) {
      println("\n")
      graph.edges.take(edges).foreach(println)
    }
  }
}
