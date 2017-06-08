package com.garciparedes.sparkexamples.main

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.random.RandomRDDs.uniformRDD
import org.apache.spark.rdd.RDD

import scala.math.pow
/**
  * Created by lnahoom on 22/08/2016.
  */
object RandomVectorGenerator {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("Datasets Test")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    val alpha: Double = 0.75
    val n: Long = pow(10,6).toLong

    val randomDouble: RDD[Double] = uniformRDD(sc, n)
    randomDouble.take(10).foreach(println)

    val randomBool: RDD[Boolean] = randomDouble.map(_ < alpha)
    randomBool.take(10).foreach(println)
  }
}
