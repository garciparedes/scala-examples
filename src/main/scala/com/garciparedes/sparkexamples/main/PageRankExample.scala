/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// scalastyle:off println
package com.garciparedes.sparkexamples.main

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.graphx.lib.PageRank
import org.apache.spark.{SparkConf, SparkContext}


/**
 * A PageRank example on social network dataset
 * Run with
 * {{{
 * bin/run-example graphx.PageRankExample
 * }}}
 */
object PageRankExample {
  def main(args: Array[String]): Unit = {
    val appName = "Edge Stream Graph Spark Example"
    val conf = new SparkConf()
    conf.setAppName(appName)
    conf.setMaster("local[*]")

    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    // Load the edges as a graph
    val graph = GraphLoader.edgeListFile(sc, "./datasets/followers.txt")
    // Run PageRank
    val ranks = graph.pageRank(0.01).vertices
    // Join the ranks with the usernames
    val users = sc.textFile("./datasets/users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }
    val ranksByUsername = users.join(ranks).map {
      case (id, (username, rank)) => (username, rank)
    }
    // Print the result
    println(ranks.collect().mkString("\n"))
    // $example off$
    sc.stop()
  }
}
// scalastyle:on println
