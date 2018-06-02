package com.garciparedes.hackerrank.fp.introduction

import scala.io

object UpdateList extends App {

  def f(arr: List[Int]): List[Int] = arr.map(_.abs)


  val list: List[Int] = Iterator
    .continually(io.StdIn.readLine)
    .takeWhile(_.nonEmpty)
    .map(_.toInt)
    .toList

  println(f(list))
}
