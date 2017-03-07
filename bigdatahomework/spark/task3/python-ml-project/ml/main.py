from pyspark.mllib.linalg import Vectors
from pyspark.mllib.regression import LabeledPoint
from pyspark import SparkConf, SparkContext
from pyspark.mllib.tree import GradientBoostedTrees
from pyspark.mllib.evaluation import BinaryClassificationMetrics
import re

def comma_to_dot(x):
    if ',' in x:
        return x.replace(',', '.')
    else:
        return x

def NaN_to_0(x):
    if x == 'NaN':
        return -100
    else:
        return x

def normilize(s):
    arr = s.split(';')
    for i in range(len(arr)):
        arr[i] = comma_to_dot(arr[i])
        arr[i] = NaN_to_0(arr[i])
    return arr

def get_catogiracal_fatures():
    categoricalFeaturesInfo = {}
    categoricalFeaturesInfo[1] = 2
    categoricalFeaturesInfo[2] = 2
    categoricalFeaturesInfo[3] = 2
    categoricalFeaturesInfo[6] = 7
    categoricalFeaturesInfo[7] = 5
    categoricalFeaturesInfo[8] = 31
    categoricalFeaturesInfo[9] = 12
    categoricalFeaturesInfo[10] = 12
    categoricalFeaturesInfo[11] = 2
    categoricalFeaturesInfo[12] = 10
    categoricalFeaturesInfo[13] = 6
    return categoricalFeaturesInfo

def toLabeledPoint(tupl):
    row = normilize(tupl[1])
    lable = tupl[0]
    features = Vectors.dense(row)
    return LabeledPoint(lable, features)

def trainForest(train):
    loss = 'leastSquaresError'
    numIterations = 10
    learningRate = 0.1
    maxDepth = 5
    maxBins = 32
    return GradientBoostedTrees.trainClassifier(train, get_catogiracal_fatures(),
            loss, numIterations, learningRate, maxDepth, maxBins)

def filterNaN(s):
    if re.compile("(NaN;)+.*(NaN;)+").findall(s):
        return False
    else:
        return True

def prepareData(sc, target_path, object_path):
    targets = sc.textFile(target_path, 1)
    objects = sc.textFile(object_path, 1)
    zipped = targets.zip(objects)
    return zipped.filter(lambda f: filterNaN(f[1])).map(lambda x: toLabeledPoint(x)).cache()

def predictOnTest(model, test):
    predictions = model.predict(test.map(lambda x: x.features))
    return test.map(lambda p: p.label).zip(predictions)

def measure(l_a_p, test_size):
    metrics = BinaryClassificationMetrics(l_a_p)
    auROC = metrics.areaUnderROC
    err = l_a_p.filter(lambda (v, p): v != p).count() / float(test_size)
    ok = l_a_p.filter(lambda (v, p): v == p).count() / float(test_size)
    print "Area under ROC = {0}, errors = {1}, ok = {2}".format(auROC, err, ok)

def main():
    target_path = "/training/spark/ml/Target.csv"
    object_path = "/training/spark/ml/Objects.csv"
    conf = SparkConf().setMaster("local").setAppName("training-3")
    sc = SparkContext(conf=conf)
    labeledFeatures = prepareData(sc, target_path, object_path)
    train, test = labeledFeatures.randomSplit([0.7, 0.3], seed = 11L)
    model = trainForest(train)
    labelsAndPrdictions = predictOnTest(model, test)
    measure(labelsAndPrdictions, test.count())
    sc.stop()

main()
