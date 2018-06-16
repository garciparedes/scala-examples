package com.garciparedes.hackerrank.fp.introduction

import scala.io

object ComputeThePerimeterOfAPolygon extends App {

  def read: String = io.StdIn.readLine.trim

  val points = (1 to read.toInt)
    .map(_ => read.split(' ').map(_.toInt))
    .map { case Array(f1, f2) => (f1, f2) }

  val p = (points, points.drop(1) ++ List(points.head))
    .zipped
    .map((a, b) => {
      math.sqrt(math.pow(a._1 - b._1, 2) + math.pow(a._2 - b._2, 2))
    })
    .sum

  println(p)
}
