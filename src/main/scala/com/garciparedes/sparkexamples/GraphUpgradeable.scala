package com.garciparedes.sparkexamples

import org.apache.spark.SparkContext
import org.apache.spark.graphx.PartitionStrategy.RandomVertexCut
import org.apache.spark.graphx._
import org.apache.spark.storage.StorageLevel

/**
  * Created by garciparedes on 07/05/2017.
  */
class GraphUpgradeable(var sc: SparkContext, var graph: Graph[Int, Int]) extends Serializable {

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
        graph.edges.union(
          sc.parallelize(edgeList).map((e) => Edge(e._1, e._2))),
        1,
        StorageLevel.MEMORY_AND_DISK,
        StorageLevel.MEMORY_AND_DISK
      ).partitionBy(RandomVertexCut)
        .groupEdges((a, b) => a + b)
    } else {
      graph = Graph.fromEdges(
        sc.parallelize(edgeList).map((e) => Edge(e._1, e._2)),
        1,
        StorageLevel.MEMORY_AND_DISK,
        StorageLevel.MEMORY_AND_DISK
      )
    }
  }

  def print(edges: Int = 100): Unit = {
    if (graph != null) {
      println("\n")
      graph.edges.take(edges).foreach(println)
    }
  }
}
