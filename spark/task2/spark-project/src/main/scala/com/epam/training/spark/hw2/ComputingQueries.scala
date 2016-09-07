package com.epam.training.spark.hw2

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{DataFrame, SQLContext}

/**
  * @author Anton_Solovev
  * @since 9/6/2016.
  */
class ComputingQueries(sqlContext: SQLContext, hiveContext: HiveContext) {

  import sqlContext.implicits._

  //created tables in Hive homework 4
  var flightsTable = None: Option[DataFrame]
  var airportsTable = None: Option[DataFrame]
  var carrierTable = None: Option[DataFrame]

  def loadData(): Unit = {
    val airportsDF = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("/training/hive_dataset/airports.csv")

    //    hiveContext.sql("CREATE EXTERNAL TABLE IF NOT EXISTS airportsSpark " +
    //      "(iata String, airport String, city String, state String, country String, lat String, long String) " +
    //      "ROW FORMAT " +
    //      "DELIMITED FIELDS TERMINATED BY ',' " +
    //      "STORED AS TEXTFILE")
    //    hiveContext.sql("LOAD DATA INPATH '/training/hive_dataset/airports.csv' OVERWRITE INTO TABLE airportsSpark")
    //    val airportsDF = hiveContext.sql("SELECT * FROM airportsSpark")

    airportsDF.show

    val flightsDF = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("/training/hive_dataset/2007.csv")
    flightsDF.show

    val carriersDF = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("/training/hive_dataset/carriers.csv")
    carriersDF.show

    flightsTable = Option(flightsDF)
    airportsTable = Option(airportsDF)
    carrierTable = Option(carriersDF)
  }

  //  3. Count total number of flights per carrier in 2007
  def totalNumberOfFlights(): Unit = {
    val fl = flightsTable.get

    fl.groupBy("UniqueCarrier")
      .count()
      .show()
  }

  //  4. The total number of flights served in Jun 2007 by NYC
  def flightsServedInJun(): Unit = {
    val air = airportsTable.get
    val fl = flightsTable.get

    val originCount = fl.as('fl)
      .join(air.as('a), $"fl.Origin" === $"a.iata")
      .filter($"a.city" === "New York")
      .where($"fl.Month" === "6")
      .count()

    val destCount = fl.as('fl)
      .join(air.as('a), $"fl.Dest" === $"a.iata")
      .filter($"a.city" === "New York")
      .where($"fl.Month" === "6")
      .count()

    val res = originCount + destCount
    Console.println("The total number of flights served in Jun 2007 by NYC is " + res)
  }

  //  5. Find five most busy airports in US during Jun 01 - Aug 31
  def findFiveAirports(): Unit = {
    val air = airportsTable.get
    val fl = flightsTable.get

    val joined1 = fl.as('fl)
      .join(air.as('a), $"fl.Origin" === $"a.iata")
      .where($"fl.Month" <= "8")
      .where($"fl.DayofMonth" <= "31")
      .where($"fl.Month" >= "6")
      .where($"fl.Month" >= "1")
      .filter($"a.country" === "USA")
      .select($"fl.Origin")
      .toDF("us_aps")

    val joined2 = fl.as('fl)
      .join(air.as('a), $"fl.Dest" === $"a.iata")
      .where($"fl.Month" <= "8")
      .where($"fl.DayofMonth" <= "31")
      .where($"fl.Month" >= "6")
      .where($"fl.Month" >= "1")
      .filter($"a.country" === "USA")
      .select($"fl.Dest")
      .toDF("us_aps")

    joined1.unionAll(joined2)
      .groupBy("us_aps")
      .count()
      .sort($"count".desc)
      .limit(5)
      .show()
  }

  //  6. Find the carrier who served the biggest number of flights
  def finTheCarrier(): Unit = {
    val fl = flightsTable.get

    fl.groupBy("UniqueCarrier")
      .count()
      .sort($"count".desc)
      .limit(1)
      .show()
  }


}
