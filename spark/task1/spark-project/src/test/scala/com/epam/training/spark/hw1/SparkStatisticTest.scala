package com.epam.training.spark.hw1

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.rdd.RDD
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Anton_Solovev
  * @since 9/1/2016.
  */
class SparkStatisticTest extends FlatSpec with Matchers with SharedSparkContext {

  val inputText =
    "ip1 - - [24/Apr/2011:04:06:01 -0400] \"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\" 200 40028 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\"\n" +
      "ip3 - - [24/Apr/2011:04:10:19 -0400] \"GET /~strabal/grease/photo1/97-13.jpg HTTP/1.1\" 200 56928 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\"\n" +
      "ip2 - - [24/Apr/2011:04:14:36 -0400] \"GET /~strabal/grease/photo9/927-5.jpg HTTP/1.1\" 200 42011 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\"\n" +
      "ip1 - - [24/Apr/2011:04:18:54 -0400] \"GET /~strabal/grease/photo1/T97-4.jpg HTTP/1.1\" 200 6244 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\""

  behavior of "Rdd"

  it should "be computing and written as csv" in {
    val rdd = sc.parallelize(inputText.split("\n"))
    val statistic = new SparkStatistic(sc)
    val computedRDD: RDD[(String, Long, Long)] = statistic.compute(rdd)
    //        40028 + 6244 = 46272, 46272 / 2 = 23136
    val expected = sc.parallelize(
      Seq(
        ("ip3", 56928L, 56928L),
        ("ip1", 23136L, 46272L),
        ("ip2", 42011L, 42011L))
    )
    assertResult(expected.collect)(computedRDD.collect)
  }

}
