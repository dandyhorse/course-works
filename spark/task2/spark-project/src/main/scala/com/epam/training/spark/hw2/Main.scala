package com.epam.training.spark.hw2

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Anton_Solovev 
  * @since 9/6/2016.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("training 1")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val hiveContext = new HiveContext(sc)
    val task = new ComputingQueries(sqlContext, hiveContext)
    task.loadData()
    task.totalNumberOfFlights()
    task.flightsServedInJun()
    task.findFiveAirports()
    task.finTheCarrier()
    sc.stop()
  }

}
