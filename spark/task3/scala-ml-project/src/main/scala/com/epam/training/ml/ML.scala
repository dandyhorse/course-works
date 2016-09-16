package com.epam.training.ml

import java.util.regex.Pattern

import org.apache.spark.SparkContext
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
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

  import scala.collection.JavaConverters._

  def prepareData(data: RDD[(String, String)]): Array[RDD[LabeledPoint]] = {
    val supervisedData = data.filter(f => {
        val p = Pattern.compile("(NaN;)+(.)*(NaN;)+")
        !p.matcher(f._2).find()
      })
      .map(toLabeledPoint)
    supervisedData.randomSplit(Array(0.7, 0.3), seed = 11L)
  }

  def toLabeledPoint(s: (String, String)): LabeledPoint = {
    val cleanedRow = s._2.replace(",", ".").replace("NaN", "0")
    val parsedRowInArray = cleanedRow.split(";").map(_.toDouble)
    val features = Vectors.dense(parsedRowInArray)
    LabeledPoint(s._1.toDouble, features)
  }

  def test(model: GradientBoostedTreesModel, test: RDD[LabeledPoint]) = {
    test.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
  }

  def measure(labelsAndPredictions: RDD[(Double, Double)], testSize: Long) = {
    val metrics = new BinaryClassificationMetrics(labelsAndPredictions)
    val auROC = metrics.areaUnderROC()

    val testErr = labelsAndPredictions.filter(r => r._1 != r._2).count().toDouble / testSize
    Console.println("Test Error = " + testErr)
    Console.println("Area under ROC = " + auROC)
  }

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

  def train(train: RDD[LabeledPoint]): GradientBoostedTreesModel = {
    trainForestModel(train)
    //    trainDecisionTree(train)
  }

  def trainDecisionTree(train: RDD[LabeledPoint]): DecisionTreeModel = {
    val numClasses = 2
    val impurity = "gini"
    val maxDepth = 5
    val maxBins = 32

    DecisionTree.trainClassifier(train, numClasses, getCategoricalFeatures,
      impurity, maxDepth, maxBins)
  }

  def trainForestModel(train: RDD[LabeledPoint]): GradientBoostedTreesModel = {
    val numClasses = 2
    val numIterations = 10
    val maxDepth = 5
    val maxBins = 32

    val javaMap = getCategoricalFeatures.map { case (k, v) => (int2Integer(k), int2Integer(v)) }.asJava

    val boostingStrategy = BoostingStrategy.defaultParams("Classification")
    boostingStrategy.setNumIterations(numIterations)
    boostingStrategy.treeStrategy.setNumClasses(numClasses)
    boostingStrategy.treeStrategy.setMaxDepth(maxDepth)
    boostingStrategy.treeStrategy.setMaxBins(maxBins)
    boostingStrategy.treeStrategy.setCategoricalFeaturesInfo(javaMap)
    GradientBoostedTrees.train(train, boostingStrategy)
  }

}
