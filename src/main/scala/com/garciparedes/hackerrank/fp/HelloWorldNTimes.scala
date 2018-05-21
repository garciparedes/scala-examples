package com.garciparedes.hackerrank.fp

import scala.io.Source.stdin

object HelloWorldNTimes extends App {


  def f(n: Int): Unit = {
    n match {
      case x if x > 0 =>
        println("Hello World")
        f(x - 1)
      case _ =>
    }
  }



  var n = scala.io.StdIn.readInt
  f(n)
}
