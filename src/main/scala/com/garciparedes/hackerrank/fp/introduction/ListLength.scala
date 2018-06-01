package com.garciparedes.hackerrank.fp.introduction

import scala.annotation.tailrec
import scala.io

object ListLength extends App {

  def f(arr: List[Int]): Int = arr.foldLeft(0)((acc, _) => acc + 1)

  val list: List[Int] = Iterator
    .continually(io.StdIn.readLine)
    .takeWhile(_.nonEmpty)
    .map(_.toInt)
    .toList

  println(f(list))
}
