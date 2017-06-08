package com.garciparedes.sparkexamples.main

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by lnahoom on 22/08/2016.
  */
object TransitionMatrixExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("Datasets Test")
    conf.setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val aM = sc.parallelize(Array(
      Array(0, 1, 1, 0),
      Array(0, 0, 1, 0),
      Array(1, 0, 0, 0),
      Array(0, 0, 1, 0)
    ))

    var v = sc.parallelize(Array(
      0.25,
      0.25,
      0.25,
      0.25
    ))

    val n = v.count
    val beta = 0.85

    println(aM.map((r) => s"${r.mkString("\t")}\n").collect.reduceLeft(_.concat(_)))

    val tM = aM.map(r => r.map(c => {
      c.toFloat / r.sum
    }))

    println(tM.map(r => s"${r.mkString("\t")}\n").collect.reduceLeft(_.concat(_)))

    /*

    val tMPR = aM.zipWithIndex.map(
      r => r._1.zipWithIndex.map(
        c => {
          if (r._2 != c._2) beta * c._1.toFloat / r._1.sum + (1 - beta) / n else 0.0
        })
        .map(c => scala.math.round(c * 1000.0) / 1000.0)
    )

    println(tMPR.map(r => s"${r.mkString("\t")}\n").collect.reduceLeft(_.concat(_)))

    */
  }
}