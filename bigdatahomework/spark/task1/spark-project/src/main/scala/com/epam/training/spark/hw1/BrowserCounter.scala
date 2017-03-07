package com.epam.training.spark.hw1

import eu.bitwalker.useragentutils.Manufacturer
import org.apache.spark.SparkContext

/**
  * @author Anton_Solovev 
  * @since 9/3/2016.
  */
class BrowserCounter(sc: SparkContext) extends Serializable {

  private object Browser extends Serializable {
    val MOZILLA = Manufacturer.MOZILLA.getName
    val MICROSOFT = Manufacturer.MICROSOFT.getName
    val OTHER = Manufacturer.OTHER.getName
  }

  private val accumulatorMap = Map(
    (Browser.MOZILLA, sc.accumulator(0, Browser.MOZILLA)),
    (Browser.MICROSOFT, sc.accumulator(0, Browser.MICROSOFT)),
    (Browser.OTHER, sc.accumulator(0, Browser.OTHER))
  )

  def print() = {
    accumulatorMap.foreach(f => Console.println(String.format("%s ===> %s", f._1, f._2)))
  }

  def accumulate(b: String) = {
    val browser = if (b != null) b else Browser.OTHER
    browser match {
      case Browser.MOZILLA => accumulatorMap(Browser.MOZILLA) += 1
      case Browser.MICROSOFT => accumulatorMap(Browser.MICROSOFT) += 1
      case _ => accumulatorMap(Browser.OTHER) += 1
    }
  }

}
