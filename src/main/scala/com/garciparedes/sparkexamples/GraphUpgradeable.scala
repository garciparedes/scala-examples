package com.garciparedes.sparkexamples

import org.apache.spark.SparkContext
import org.apache.spark.graphx.PartitionStrategy.RandomVertexCut
import org.apache.spark.graphx._

/**
  * Created by garciparedes on 07/05/2017.
  */
class GraphUpgradeable(var sc: SparkContext, var graph: Graph[Int, Double]) extends Serializable {

  def this(sc: SparkContext) {
    this(sc, null)
  }

  def addToGraph(edge: (Long, Long)): Any = {
    addToGraph(Array(edge))
  }

  def addToGraph(edgeList: Array[(Long, Long)]): Any = {
    edgeList.foreach(println)
    if (graph != null) {
      graph = Graph.fromEdges(
        graph.edges.union(sc.parallelize(edgeList).map((e) => Edge(e._1, e._2, 1.0))), 1)
        .partitionBy(RandomVertexCut).
        groupEdges((attr1, attr2) => attr1 + attr2)
    } else {
      graph = Graph.fromEdges(sc.parallelize(edgeList).map((e) => Edge(e._1, e._2, 1.0)), 1)
    }
  }

  def print(edges: Int = 100): Unit = {
    if (graph != null) {
      println("\n")
      graph.edges.take(edges).foreach(println)
    }
  }
}
