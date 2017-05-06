
package sparkexamples

import java.util.Calendar

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx._


object BigDynamicGraphSparkExample2 {
  def main(args: Array[String]): Unit = {


    val conf = new SparkConf()
    conf.setAppName("Dynamic Graph Spark Example")
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)

    val pagerankItRers = 2
    val topKResults = 100
    val graphFile = "./datasets/wiki-Vote.txt"

    println(Calendar.getInstance().getTime + "\tStart!")

    val graph: Graph[Int, Int] = GraphLoader.edgeListFile(sc, graphFile)
    println(Calendar.getInstance().getTime + "\tDone Load!")

    var graph_incremental: Graph[Int, Int] = Graph(graph.vertices, sc.parallelize(Array(Edge(1L, 3L, 1))))

    graph.edges.map((e) => {
      graph_incremental = Graph(graph_incremental.vertices, graph_incremental.edges.union(
        sc.parallelize(Array(e))
      ))
    })


    println(Calendar.getInstance().getTime + "\tDone Update!")


    println(Calendar.getInstance().getTime + "\tDone Second PageRank!")

    sc.stop()
    println(Calendar.getInstance().getTime + "\tFinish!")

  }
}
