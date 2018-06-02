package com.garciparedes.hackerrank.fp.introduction

import scala.io

object EvalEx extends App {

  def sum(a: Double, b: Double): Double = a + b

  def mul(a: Int, b: Int): Int = a * b

  def fact(a: Int): Int = a match {
    case x if x > 1 => (2 to x).reduce(mul)
    case 0 | 1 => 1
    case _ => throw new IllegalArgumentException("a must be positive.")
  }

  def f(arr: List[Double], terms: Int = 10): List[Double] = arr.map(a => {
    (0 until terms).map(i => math.pow(a, i) / fact(i)).reduce(sum)
  })

  val num: Int = io.StdIn.readInt
  val list: List[Double] = (1 to num).map(_ => io.StdIn.readDouble).toList

  f(list)
    .foreach(println)

}