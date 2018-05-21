package com.garciparedes.hackerrank.fp

import scala.io.Source.stdin

object SolveMeFirst {

  def main(args: Array[String]) {

    println(stdin.getLines().take(2).map(_.toInt).sum)

  }
}
