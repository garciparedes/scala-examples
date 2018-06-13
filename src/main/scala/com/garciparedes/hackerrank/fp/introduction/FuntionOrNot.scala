package com.garciparedes.hackerrank.fp.introduction

import scala.io

object FuntionOrNot extends App {

  def read: String = io.StdIn.readLine.trim

  (1 to read.toInt)
    .map(_ => {
      (1 to read.toInt)
        .map(_ => read.split(' ').map(_.toInt))
        .groupBy(_.head)
        .map(pair => (pair._1, pair._2.distinct.size == 1))
        .forall(_._2)
    })
    .map(x => if (x) "YES" else "NO")
    .foreach(println)
}
