package com.garciparedes.breezeexamples.main

import breeze.linalg.{Axis, DenseMatrix, DenseVector, sum}
import breeze.linalg.operators._

import scala.io.Source
import scala.math.{pow, round}


/**
  * Created by lnahoom on 22/08/2016.
  */
object NaivePageRankExampleWikiVote {
  def main(args: Array[String]): Unit = {

    val graphFile = "./datasets/followers.txt"

    val pattern = """^([0-9]+)[\t ]+([0-9]+)$""".r


    val truncate: (Double, Int) => Double = (a, decimals) => {
      round(a * pow(10, decimals)) / pow(10, decimals)
    }

    val adjacencyToTransition: (DenseMatrix[Double], Int, Double) => DenseMatrix[Double] = (aM, n, beta) => {

      val c = sum(aM, Axis._0).t

       println(c)
      //beta * (aM / c) + (1- beta) / n

      //beta *:* (aM /:/ c) + (1-beta) /:/ n
      aM

      /*
      Matrices.dense(n, n, aM.rowIter.map(
        r => r.toArray.map(c => {
          if (r.toArray.sum != 0)
            beta * (c / r.toArray.sum) + (1 - beta) / n
          else
            1.0 / n
        })
      ).reduce((a, b) => a ++ b))
      */
    }

    val n: Int = 7
    val beta: Double = 0.85

    val arrayM: Array[Double] = Array.fill[Double](n * n)(0)

    println(arrayM.size)

    Source.fromFile(graphFile).getLines.foreach((line: String) => {
      line match {
        case pattern(v1, v2) => {
          try {
            arrayM((v2.toInt - 1) + n * (v1.toInt - 1)) = 1
          } catch {
            case e: Exception => println(e)
          }
        }
        case _ => None
      }
    })

    var aM: DenseMatrix[Double] = new DenseMatrix(n, n, arrayM)


    //aM.rowIter.foreach(println)

    var tM = adjacencyToTransition(aM, n, beta)
    //tM.rowIter.foreach(println)


    val vArray: Array[Double] = Array.fill[Double](n)(1.0 / n)
    var v: DenseVector[Double] =new DenseVector(vArray)

    println(s"[${v.toArray.mkString("\t")}]")


    for (i <- 1 to 10) {
      v = tM * v
    }
    println(s"[${v.toArray.map(truncate(_, 4)).mkString("\t")}]")
  }
}