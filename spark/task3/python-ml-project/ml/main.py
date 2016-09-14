from pyspark.mllib.linalg import Vectors
from pyspark.mllib.regression import LabeledPoint
from pyspark import SparkConf, SparkContext
from pyspark.mllib.tree import GradientBoostedTrees
from pyspark.mllib.evaluation import BinaryClassificationMetrics

def comma_to_dot(x):
    if ',' in x:
        return x.replace(',', '.')
    else:
        return x

def NaN_to_0(x):
    if x == 'NaN':
        return 0
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
    row = tupl[0]
    lable = tupl[1]
    features = Vectors.dense(normilize(row))
    return LabeledPoint(lable, features)

def trainForest(sc, train):
    loss = 'leastSquaresError'
    numIterations = 10
    learningRate = 0.5
    maxDepth = 5
    maxBins = 32
    return GradientBoostedTrees.trainClassifier(train, get_catogiracal_fatures(),
            loss, numIterations, learningRate, maxDepth, maxBins)

def prepareData(sc, target_path, object_path):
    zt = sc.textFile(target_path).zipWithIndex().map(lambda a: (a[1], a[0]))
    zo = sc.textFile(object_path).zipWithIndex().map(lambda a: (a[1], a[0]))
    return zo.join(zt).map(lambda x: toLabeledPoint(x[1]))

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
    train, test = labeledFeatures.randomSplit([0.6, 0.4], seed = 11L)
    model = trainForest(sc, train)
    labelsAndPrdictions = predictOnTest(model, test)
    measure(labelsAndPrdictions, test.count())
    sc.stop()

main()
