package com.garciparedes.hackerrank.fp.introduction

import scala.io.Source.stdin

object SolveMeFirst extends App {

  println(stdin.getLines().take(2).map(_.toInt).sum)
}
