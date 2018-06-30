package com.garciparedes.hackerrank.fp.recursion

import scala.io

object PascalTriangle extends App {

  def fact(n: Int): Int = {
    (1 to n).product
  }

  (0 until io.StdIn.readInt()).map(n => {
    (0 to n).map(r => {
      fact(n) / (fact(r) * fact(n - r))
    })
  }).map(row => {
      row.mkString(" ")
  }).foreach(println)
}
