package com.garciparedes.hackerrank.fp

import scala.io

object SumOfOddElements extends App {

  def isOdd(n: Int): Boolean = n % 2 != 0

  def sum(a: Int, b: Int): Int = a + b

  def f(arr: List[Int]): Int = arr
    .filter(isOdd)
    .reduce(sum)

  val list: List[Int] = Iterator
    .continually(io.StdIn.readLine)
    .takeWhile(_.nonEmpty)
    .map(_.toInt)
    .toList

  println(f(list))
}
