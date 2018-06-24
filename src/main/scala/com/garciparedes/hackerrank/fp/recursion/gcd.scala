package com.garciparedes.hackerrank.fp.recursion

import scala.annotation.tailrec
import scala.io

object gcd extends App {

  @tailrec
  def gcd(x: Int, y: Int): Int = {
    if (x != 0 && y != 0) {
      if (x > y) {
        gcd(x % y, y)
      } else {
        gcd(x, y % x)
      }
    } else {
      Math.max(x, y)
    }
  }

  val numbers: Array[Int] = io.StdIn.readLine.trim.split(' ').map(_.toInt)
  println(gcd(numbers.head, numbers.reverse.head))
}
