package com.garciparedes.hackerrank.fp.introduction

import scala.io

object AreaUnderCurvesAndVolumeOfRevolvingACurv extends App {

  def sum(a: Double, b: Double): Double = a + b

  def f(coefficients: List[Int], powers: List[Int], x: Double): Double = {
    (coefficients, powers)
      .zipped
      .map((a, b) => a * math.pow(x, b))
      .reduce(sum)
  }

  def area(coefficients: List[Int], powers: List[Int], x: Double): Double = {
    math.pow(f(coefficients, powers, x), 2) * math.Pi
  }

  def summation(func: (List[Int], List[Int], Double) => Double,
                upperLimit: Double, lowerLimit: Double,
                coefficients: List[Int], powers: List[Int],
                step: Double = 0.001): Double = {
    (lowerLimit to upperLimit by step)
      .map(v => func(coefficients, powers, v))
      .reduce(sum) * step
  }

  val coefficients: List[Int] = io
    .StdIn.readLine
    .split(' ')
    .map(_.toInt)
    .toList
  val powers: List[Int] = io
    .StdIn.readLine
    .split(' ')
    .map(_.toInt)
    .toList

  val limits: List[Double] = io
    .StdIn.readLine
    .split(' ')
    .map(_.toDouble)
    .toList

  printf("%.2f\n%.2f\n",
    summation(f, limits.last, limits.head, coefficients, powers),
    summation(area, limits.last, limits.head, coefficients, powers))
}