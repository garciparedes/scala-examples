package com.garciparedes.sparkexamples.main

import org.apache.spark.ml.linalg.{Matrices, Matrix, Vector, Vectors}
import org.apache.spark.{SparkConf, SparkContext}

import scala.math.{pow, round}


/**
  * Created by lnahoom on 22/08/2016.
  */
object NaivePageRankExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("Datasets Test")
    conf.setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val truncate: (Double, Int) => Double = (a, decimals) => {
      round(a * pow(10, decimals)) / pow(10, decimals)
    }


    val aM: Matrix = Matrices.dense(4, 4, Array(
      0, 0, 1, 1,
      1, 0, 0, 0,
      1, 1, 0, 1,
      1, 1, 0, 0
    ))

    aM.rowIter.foreach(println)


    val tM = Matrices.dense(4, 4,
      aM.rowIter.map(r => r.toArray.map(c => c / r.toArray.sum)).reduce((a, b) => a ++ b))
    tM.rowIter.foreach(println)


    var v: Vector = Vectors.dense(0.25, 0.25, 0.25, 0.25)

    val n: Int = v.size
    val beta: Double = 0.85

    println(s"[${v.toArray.mkString("\t")}]")

    for (i <- 1 to 10) {
      v = tM.multiply(v)
      println(s"[${v.toArray.map(truncate(_, 4)).mkString("\t")}]")
    }
  }
}