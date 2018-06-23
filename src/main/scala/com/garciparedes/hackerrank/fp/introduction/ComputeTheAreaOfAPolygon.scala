package com.garciparedes.hackerrank.fp.introduction

import scala.io

object ComputeTheAreaOfAPolygon extends App {

  def read: String = io.StdIn.readLine.trim

  val points = (1 to read.toInt)
    .map(_ => read.split(' ').map(_.toInt))
    .map { case Array(f1, f2) => (f1, f2) }

  val area = math.abs((points, points.drop(1) :+ points.head)
    .zipped
    .map((a, b) => {
      a._1 * b._2 - b._1 * a._2
    })
    .sum) / 2.0

  println(area)
}
