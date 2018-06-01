package com.garciparedes.hackerrank.fp.introduction

import scala.io

object HelloWorldNTimes extends App {

  def f(n: Int): Unit = (1 to n)
    .foreach(_ => println("Hello World"))

  var n = io.StdIn.readInt

  f(n)
}
