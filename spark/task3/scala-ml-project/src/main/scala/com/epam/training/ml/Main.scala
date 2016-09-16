package com.epam.training.ml

/**
  * @author Anton_Solovev 
  * @since 9/13/2016.
  */


import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Anton_Solovev
  * @since 9/12/2016.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("training-3")
    val sc: SparkContext = new SparkContext(conf)

    val features = sc.textFile("/training/spark/ml/Objects.csv", 1)
    val labels = sc.textFile("/training/spark/ml/Target.csv", 1)

    val rawLabelsAndFeatures = labels.zip(features)
    val learning = new ML(sc)

    val splitOfDate = learning.prepareData(rawLabelsAndFeatures)
    val model = learning.train(splitOfDate(0))
    val labelsAndPredictions = learning.test(model, splitOfDate(1))

    learning.measure(labelsAndPredictions, splitOfDate(1).count())
  }

}