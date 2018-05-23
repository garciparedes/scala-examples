package com.garciparedes.hackerrank.fp


import scala.annotation.tailrec
import scala.io

object FilterPositionsInAList extends App {

  def f(arr: List[Int]):List[Int] = {
    @tailrec
    def fRec(arr1: List[Int], arr2:List[Int] = List()): List[Int] = {
      if (arr1.length < 2) arr2
      else fRec(arr1.drop(2), arr2 :+ arr1(1))
    }
    fRec(arr)
  }

  val list: List[Int] = Iterator.continually(io.StdIn.readLine)
    .takeWhile(_.nonEmpty)
    .map(_.toInt)
    .toList

  f(list).foreach(println)
}
