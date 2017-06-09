package com.garciparedes.sparkexamples.main

import org.apache.spark.ml.linalg.{Matrices, Matrix, Vector, Vectors}
import org.apache.spark.{SparkConf, SparkContext}

import scala.math.{pow, round}


/**
  * Created by lnahoom on 22/08/2016.
  */
object ResetPageRankExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("Datasets Test")
    conf.setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val truncate: (Double, Int) => Double = (a, decimals) => {
      round(a * pow(10, decimals)) / pow(10, decimals)
    }

    val adjacencyToTransition: (Matrix, Int, Double) => Matrix = (aM, n, beta) => {
      Matrices.dense(n, n, aM.rowIter.map(
        r => r.toArray.map(c => {
          if (r.toArray.sum != 0)
            beta * (c / r.toArray.sum) + (1 - beta) / n
          else
            1.0 / n
        })
      ).reduce((a, b) => a ++ b))
    }

    val n: Int = 4
    val beta: Double = 0.85

    var aM: Matrix = Matrices.dense(n, n, Array(
      0, 0, 1, 1,
      1, 0, 0, 0,
      1, 1, 0, 1,
      1, 1, 0, 0
    ))
    //aM.rowIter.foreach(println)

    var tM = adjacencyToTransition(aM, n, beta)
    tM.rowIter.foreach(println)

    var v: Vector = Vectors.dense(0.25, 0.25, 0.25, 0.25)

    println(s"[${v.toArray.mkString("\t")}]")


    for (i <- 1 to 10) {
      v = tM.multiply(v)
      println(s"[${v.toArray.map(truncate(_, 4)).mkString("\t")}]")
    }


    aM = Matrices.dense(n, n, Array(
      0, 0, 1, 1,
      1, 0, 0, 1,
      1, 1, 0, 0,
      0, 1, 1, 0
    ))

    //aM.rowIter.foreach(println)

    tM = adjacencyToTransition(aM, n, beta)
    tM.rowIter.foreach(println)

    for (i <- 1 to 10) {
      v = tM.multiply(v)
      println(s"[${v.toArray.map(truncate(_, 4)).mkString("\t")}]")
    }

  }
}