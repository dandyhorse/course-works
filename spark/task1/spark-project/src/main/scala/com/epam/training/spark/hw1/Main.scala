package com.epam.training.spark.hw1

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Anton_Solovev 
  * @since 9/1/2016.
  */
object Main {

  val baseUrl = "hdfs://sandbox.hortonworks.com"

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("training 1").setMaster("local")
    val sc = new SparkContext(conf)
    val job = new SparkStatistic(sc)
    if (args.length != 2) {
      System.err.println(" Usage: Main < input path > < output path >")
      System.exit(-1)
    }
    val rdd = sc.textFile(baseUrl + args(0))
    val result = job.compute(rdd)
    val out = result.sortBy[Long](f => f._3, ascending = false, 1)
    out.saveAsTextFile(baseUrl + args(1))
    out.take(5).foreach(println)
    sc.stop()
  }

}
