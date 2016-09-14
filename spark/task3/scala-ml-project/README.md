## Spark Machine Learning project

Before add features and targets files onto hdfs

Build
```
cd scala-ml-project
sbt assembly
```

Run
```
spark-submit \
--class com.epam.training.ml.Main \
--master local[*] \
--name "training-3" \
/media/sf_share/scala-ml-project/target/scala-2.10/ml-hw3.jar 
```
