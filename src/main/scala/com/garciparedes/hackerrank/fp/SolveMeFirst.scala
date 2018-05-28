package com.garciparedes.hackerrank.fp

import scala.io.Source.stdin

object SolveMeFirst extends App {

  println(stdin.getLines().take(2).map(_.toInt).sum)
}
