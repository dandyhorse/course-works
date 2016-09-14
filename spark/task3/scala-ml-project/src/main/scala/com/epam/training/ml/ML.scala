package com.epam.training.ml

import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.model.{DecisionTreeModel, GradientBoostedTreesModel}
import org.apache.spark.mllib.tree.{DecisionTree, GradientBoostedTrees}
import org.apache.spark.rdd.RDD

/**
  * @author Anton_Solovev 
  * @since 9/12/2016.
  */
class ML(sc: SparkContext) extends Serializable {

  def getCategoricalFeatures: Map[Int, Int] = {
    var categoricalFeaturesInfo = Map[Int, Int]()
    categoricalFeaturesInfo += (1 -> 2)
    categoricalFeaturesInfo += (2 -> 2)
    categoricalFeaturesInfo += (3 -> 2)
    categoricalFeaturesInfo += (6 -> 7)
    categoricalFeaturesInfo += (7 -> 5)
    categoricalFeaturesInfo += (8 -> 31)
    categoricalFeaturesInfo += (9 -> 12)
    categoricalFeaturesInfo += (10 -> 12)
    categoricalFeaturesInfo += (11 -> 2)
    categoricalFeaturesInfo += (12 -> 10)
    categoricalFeaturesInfo += (13 -> 6)
    categoricalFeaturesInfo
  }

  def toLabeledPoint(s: (String, String)): LabeledPoint = {
    val cleanedRow = s._2.replace(",", ".").replace("NaN", "0")
    val parsedRowInArray = cleanedRow.split(";").map(_.toDouble)
    val features = Vectors.dense(parsedRowInArray)
    LabeledPoint(s._1.toDouble, features)
  }

  def run(data: RDD[(String, String)]): RDD[(Double, Double)] = {

    val supervisedData = data.map(toLabeledPoint)
    val splits = supervisedData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val train = splits(0).cache()
    val test = splits(1)

    //    val model = trainWithSGD(train)
    //    val model = trainForestModel(train)
    val model = trainDecisionTree(train)

    return test.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
  }

  def trainDecisionTree(train: RDD[LabeledPoint]): DecisionTreeModel = {
    val numClasses = 2
    val impurity = "gini"
    val maxDepth = 10
    val maxBins = 32

    DecisionTree.trainClassifier(train, numClasses, getCategoricalFeatures,
      impurity, maxDepth, maxBins)
  }

  def trainWithSGD(train: RDD[LabeledPoint]): SVMModel = {
    val numIterations = 100
    val model = SVMWithSGD.train(train, numIterations)
    model.clearThreshold()
  }

  def trainForestModel(train: RDD[LabeledPoint]): GradientBoostedTreesModel = {
    val numClasses = 2
    val numIterations = 10
    val maxDepth = 5
    val maxBins = 32

    val boostingStrategy = BoostingStrategy.defaultParams("Classification")
    boostingStrategy.setNumIterations(numIterations)
    boostingStrategy.treeStrategy.setNumClasses(numClasses)
    boostingStrategy.treeStrategy.setMaxDepth(maxDepth)
    boostingStrategy.treeStrategy.setMaxBins(maxBins)
    boostingStrategy.treeStrategy.setCategoricalFeaturesInfo(getCategoricalFeatures)
    GradientBoostedTrees.train(train, boostingStrategy)
  }

}
