

hadoop fs -rmdir --ignore-fail-on-non-empty /training/access_logs/out

yarn jar hw3-map-reduce-1.0.jar com.epam.training.hadoop.hw3.Main /training/access_logs/input /training/access_logs/out
-Dmapreduce.compress.map.output=true
-Dmapreduce.map.output.compression.codec=org.apache.hadoop.io.compress.Snappy
