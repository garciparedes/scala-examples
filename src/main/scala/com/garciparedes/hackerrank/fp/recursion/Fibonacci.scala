package com.garciparedes.hackerrank.fp.recursion

import scala.io

object Fibonacci extends App {


  def fibonacci(x: Int): Int = {
    x match {
      case 0 | 1 => 0
      case 2 => 1
      case _ => fibonacci(x - 1) + fibonacci(x - 2)
    }
  }

  println(fibonacci(io.StdIn.readInt()))
}
