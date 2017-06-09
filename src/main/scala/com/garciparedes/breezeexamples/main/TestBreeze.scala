package com.garciparedes.breezeexamples.main

import breeze.linalg.DenseVector


/**
  * Created by lnahoom on 22/08/2016.
  */
object TestBreeze {
  def main(args: Array[String]): Unit = {

    val x = DenseVector.zeros[Double](5)

    x(1) = 1

    x.foreach(println)
  }
}