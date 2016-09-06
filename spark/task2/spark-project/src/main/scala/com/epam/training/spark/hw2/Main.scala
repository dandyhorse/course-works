package com.epam.training.spark.hw2

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Anton_Solovev 
  * @since 9/6/2016.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("training-2")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val task = new ComputingQueries(sqlContext)
    task.justLoadData()
    task.totalNumberOfFlights()
    task.flightsServedInJun()
    task.findFiveAirports()
    task.finTheCarrier()
    sc.stop()
  }

}
