package com.garciparedes.hackerrank.fp.introduction

import scala.io

object FilterArray extends App {

  def f(delim: Int, arr: List[Int]): List[Int] = arr
    .filter(_ < delim)

  val delim: Int = io.StdIn.readInt

  val list: List[Int] = Iterator
    .continually(io.StdIn.readLine)
    .takeWhile(_.nonEmpty)
    .map(_.toInt)
    .toList

  f(delim, list)
    .foreach(println)
}
