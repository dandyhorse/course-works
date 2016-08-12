export HADOOP_CLASSPATH=hadoop.hw2.jar
hadoop com.epam.training.hw2.Main /training/dataset bid_result.txt

export HADOOP_OPTS="-XX:+UseStringDeduplication"
hadoop com.epam.training.hw2.Main /training/dataset bid_result.txt
unset HADOOP_OPTS