#!/bin/bash

hadoop fs -rm -r /home/hw5/out

java -jar hw5-yarn-dist/target/hw5-yarn-dist/hw5-yarn-client-0.1.0.jar

echo "-----results-----"
hadoop fs -cat /home/hw5/out/part-r-00000
echo "-----results-----"