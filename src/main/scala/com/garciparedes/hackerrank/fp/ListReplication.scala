package com.garciparedes.hackerrank.fp

import scala.io

object ListReplication extends App{

  def f(num:Int, arr: List[Int]): List[Int] = {
    arr.flatMap((i: Int) => List.fill(num)(i))
  }

  val num: Int = io.StdIn.readInt

  val list: List[Int] = Iterator.continually(io.StdIn.readLine)
                                .takeWhile(_.nonEmpty)
                                .map(_.toInt)
                                .toList

  f(num, list).foreach(println)

}