import csv
from pyspark import SparkConf, SparkContext


def ml(sc, target_path, object_path):
    target_rdd = sc.textFile(target_path).mapPartitions(lambda x: csv.reader(x))
    target_rdd.collect()
    objects_rdd = sc.textFile(object_path).mapPartitions(lambda x: csv.reader(x, delimiter=';'))


def main():
    target_path = "/training/spark/ml/Target.csv"
    object_path = "/training/spark/ml/Objects.csv"
    conf = SparkConf().setMaster("local").setAppName("training-3")
    sc = SparkContext(conf=conf)
    ml(sc, target_path, object_path)
    sc.stop()


main()
