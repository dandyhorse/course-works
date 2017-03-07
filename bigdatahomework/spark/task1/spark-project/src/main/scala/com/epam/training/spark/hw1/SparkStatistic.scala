package com.epam.training.spark.hw1

import java.util.StringTokenizer
import java.util.regex.Pattern

import eu.bitwalker.useragentutils.UserAgent
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * @author Anton_Solovev
  * @since 9/1/2016.
  */
class SparkStatistic(sc: SparkContext) extends Serializable {

  private val BYTE_PATTERN = "((?:\\s[\\d]{3})\\s(?:[0-9])*)"
  private val CODE_OFFSET = 5
  private val browserCounter = new BrowserCounter(sc)

  def compute(rdd: RDD[String]): RDD[(String, Long, Long)] = {
    val mapReduced = rdd
      .flatMap(s => {
        val browser = new UserAgent(s).getBrowser.getManufacturer.getName
        browserCounter.accumulate(browser)
        Map((parseIp(s), (parseByte(s), 1)))
      })
      .reduceByKey((l, r) => (l._1 + r._1, l._2 + r._2))
    val computed = mapReduced.flatMap((obj) => Map((obj._1, (obj._2._1 / obj._2._2, obj._2._1))))
    computed
      .map(obj => (obj._1, obj._2._1, obj._2._2))
      .sortBy[Long](f => f._3, ascending = false, 1)
  }

  def printAccumulators(): Unit = {
    browserCounter.print()
  }

  private def parseByte(s: String): Long = {
    var result = ""
    val pattern = Pattern.compile(BYTE_PATTERN)
    val matcher = pattern.matcher(s)
    if (matcher.find()) {
      result = matcher.group().substring(CODE_OFFSET)
    }
    if (result.equals("")) {
      result = "0"
    }
    result.toLong
  }

  private def parseIp(s: String): String = {
    val tokenizer = new StringTokenizer(s)
    tokenizer.nextToken()
  }

}
