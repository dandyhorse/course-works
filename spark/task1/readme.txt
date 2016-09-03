spark-submit --class com.epam.training.spark.hw1.Main \
--master yarn-client --driver-memory 512m --executor-memory 512m --executor-cores 1 \
/media/sf_share/spark-project/target/scala-2.10/spark-project_2.10-0.0.1.jar \
/training/spark/dataset/000000 /training/spark/out