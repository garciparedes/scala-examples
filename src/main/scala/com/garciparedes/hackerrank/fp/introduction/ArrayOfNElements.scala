package com.garciparedes.hackerrank.fp.introduction

import scala.io

object ArrayOfNElements extends App {

  def f(num: Int): List[Int] = List
    .range(0, num)

  println(f(io.StdIn.readInt))
}
