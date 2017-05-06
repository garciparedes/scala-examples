package com.garciparedes.sparkexamples

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by lnahoom on 22/08/2016.
  */
object TestSpark {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("Datasets Test")
    conf.setMaster("local[2]")
    val sc = new SparkContext(conf)
    println(sc)
  }
}