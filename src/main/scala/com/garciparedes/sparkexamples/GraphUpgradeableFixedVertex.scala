package com.garciparedes.sparkexamples

import org.apache.spark.SparkContext
import org.apache.spark.graphx.PartitionStrategy.RandomVertexCut
import org.apache.spark.graphx._

/**
  * Created by garciparedes on 07/05/2017.
  */
class GraphUpgradeableFixedVertex(var sc: SparkContext, var graph: Graph[Int, Double]) extends Serializable {

  def this(sc: SparkContext, verticesCount: Int) {
    this(sc, null)

    val vertices = Array.ofDim[(Long, Int)](verticesCount)
    for (i <- 0 until verticesCount) {
      vertices(i) = (i.toLong, i)
    }
    graph = Graph(sc.parallelize(vertices), sc.parallelize(Array(Edge(1L, 1L, 1.0))))
  }

  def addToGraph(edge: (Long, Long)): Any = {
    addToGraph(Array(edge))
  }

  def addToGraph(edgeList: Array[(Long, Long)]): Any = {
    edgeList.foreach(println)
    graph = Graph(graph.vertices,
      graph.edges.union(sc.parallelize(edgeList).map((e) => Edge(e._1, e._2, 1.0)))
    ).partitionBy(RandomVertexCut).
      groupEdges((attr1, attr2) => attr1 + attr2)
  }

  def print(edges: Int = 100): Unit = {
    if (graph != null) {
      println("\n")
      graph.edges.take(edges).foreach(println)
    }
  }
}
