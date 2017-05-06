
package sparkexamples

import org.apache.spark.SparkConf
import org.apache.spark.streaming._

object StreamSparkExample {
  def main(args: Array[String]): Unit = {
    val appName = "Edge Stream Graph Spark Example"

    val conf = new SparkConf()
    conf.setAppName(appName)
    conf.setMaster("local[*]")

    val ssc = new StreamingContext(conf, Seconds(3))

    val lines = ssc.socketTextStream("localhost", 9999)

    val words = lines.flatMap(_.split("\n"))


    val lineString2TupleLong = { (line: String) => {
      val pattern = "([0-9]+)[\t ]+([0-9]+)".r
      line match {
        case pattern(v1, v2) => (v1, v2)
        case _ => Nil
      }
    }
    }

    lines.foreachRDD { rdd =>
      rdd.map(lineString2TupleLong)
        .filter(x => x != Nil)
        .foreach { record =>
          addToGraph(record)
        }
    }

    ssc.start()
    ssc.awaitTermination()

  }

  def addToGraph(edge: Any): Any = {
    println(edge)
  }

}
