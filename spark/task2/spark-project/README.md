## Spark project

Need to add files into hdfs like in Hive homework 1

Build
```
cd spark-project
sbt assembly
```

Run
```
spark-submit \
--class com.epam.training.spark.hw2.Main \
--master yarn-client \
--name "training 2" \
/media/sf_share/spark-project/target/scala-2.10/spark-hw2.jar
```
