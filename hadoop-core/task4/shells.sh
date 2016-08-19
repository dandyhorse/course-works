cd /media/sf_share/

hadoop fs -put imp /training/dataset/
hadoop fs -mkdir /training/dataset/caches
hadoop fs -put city.en.txt region.en.txt /training/dataset/caches/
hadoop fs -rm -r /training/dataset/imp_out

export HADOOP_CLASSPATH=/media/sf_share/mapreduce/target/hw4-mapreduce-1.0-jar-with-dependencies.jar 

yarn jar mapreduce/target/hw4-mapreduce-1.0-jar-with-dependencies.jar /training/dataset/imp /training/dataset/imp_out /training/dataset/caches/city.en.txt /training/dataset/caches/region.en.txt
