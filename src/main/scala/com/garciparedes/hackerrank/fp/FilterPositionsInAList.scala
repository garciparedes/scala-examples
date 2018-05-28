package com.garciparedes.hackerrank.fp


import scala.io

object FilterPositionsInAList extends App {

  def f(arr: List[Int], arr2: List[Int] = List()): List[Int] = {
    arr.length match {
      case x == 1 => arr2
      case _ => f(arr.drop(2), arr2 :+ arr(1))
    }
  }

  val list: List[Int] = Iterator.continually(io.StdIn.readLine)
    .takeWhile(_.nonEmpty)
    .map(_.toInt)
    .toList

  f(list).foreach(println)
}
