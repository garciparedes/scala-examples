package com.garciparedes.breezeexamples.main

import breeze.linalg.{DenseMatrix, DenseVector}


/**
  * Created by lnahoom on 22/08/2016.
  */
object TestBreeze {
  def main(args: Array[String]): Unit = {

    var x = DenseVector.zeros[Double](5)
    var a = DenseMatrix.ones[Double](5,5)
    x(3 to 4) := .5
    x(1) = 1

    a =  x * a

    x.foreach(println)
  }
}