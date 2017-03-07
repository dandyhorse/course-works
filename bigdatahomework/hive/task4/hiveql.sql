0. dataset from hive/task1
   tables:
      flights
      airports

1.
CREATE TABLE flightsParted (
  year String, month String, dayofmonth String, dayofweek String,
  deptime String, crsdeptime String, arrtime String, crsarrTime String,
  uniquecarrier String, flightNum String, tailnum String,
  actualelapsedtime String, crselapsedtime String, airtime String,
  arrdelay String, depdelay String,
  dest String, distance String, taxiIn String, taxiOut String,
  cancelled String, cancellationcode String, diverted String,
  carrierdelay String, weatherdelay String, nasdelay String,
  securitydelay String, lateaircraftdelay String)
PARTITIONED BY (origin String)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;

SET hive.optimize.sort.dynamic.partition=true;
SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;

INSERT OVERWRITE TABLE flightsParted PARTITION (origin)
SELECT year, month, dayofmonth, dayofweek,
  deptime, crsdeptime, arrtime, crsarrTime,
  uniquecarrier, flightNum, tailnum,
  actualelapsedtime, crselapsedtime, airtime,
  arrdelay, depdelay, dest, distance,
  taxiIn, taxiout, cancelled, cancellationcode,
  diverted, carrierdelay, weatherdelay, nasdelay,
  securitydelay, lateaircraftdelay, origin
FROM flights;

SET hive.enforce.bucketing=true;
CREATE TABLE airportsB (
  city String,
  iata String,
  airport String,
  state String,
  country String,
  lat String,
  long String)
CLUSTERED BY (city) INTO 32 BUCKETS
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;

INSERT OVERWRITE TABLE airportsB
SELECT
  city, iata, airport,
  state, country, lat, long
FROM airports;

2.firstQuery
  queries from hive/task1, but
    flights -> flightsParted
    airports -> airportsB

SELECT count(*) FROM flights AS fl
    JOIN airports ap1
       ON (fl.origin = ap1.iata)
    JOIN airports ap2
       ON (fl.dest = ap2.iata)
WHERE (ap1.city = "New York" OR ap2.city = "New York")
AND fl.month = 6;

2.secondQuery

SELECT us_aps.iadata, count(*) AS load FROM (
  SELECT fl.origin AS iadata FROM flightsParted_RC fl
      JOIN airportsB_RC AS ap ON (fl.dest = ap.iata)
       WHERE (fl.month <= 8 AND fl.dayofmonth <= 31)
       AND (fl.month >=6 AND fl.dayofmonth >=1)
       AND ap.country='USA'
  union all
  SELECT fl.dest AS iadata FROM flightsParted_RC fl
  JOIN airportsB_RC AS ap ON (fl.origin = ap.iata)
       WHERE (fl.month <= 8 AND fl.dayofmonth <= 31)
       AND (fl.month >=6 AND fl.dayofmonth >=1)
       AND ap.country='USA'
) AS us_aps
GROUP BY us_aps.iadata
SORT BY load DESC
LIMIT 5;

3.
CREATE TABLE flightsParted_RC (
  year String, month String, dayofmonth String, dayofweek String,
  deptime String, crsdeptime String, arrtime String, crsarrTime String,
  uniquecarrier String, flightNum String, tailnum String,
  actualelapsedtime String, crselapsedtime String, airtime String,
  arrdelay String, depdelay String,
  dest String, distance String,taxiIn String, taxiOut String,
  cancelled String, cancellationcode String,
  diverted String, carrierdelay String,
  weatherdelay String, nasdelay String,
  securitydelay String, lateaircraftdelay String)
PARTITIONED BY (origin String)
STORED AS RCFILE
TBLPROPERTIES ("orc.compress"="SNAPPY");

SET hive.optimize.sort.dynamic.partition=true;
SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
# SET hive.exec.max.dynamic.partitions=2000;
# SET hive.exec.max.dynamic.partitions.pernode=2000;

INSERT OVERWRITE TABLE flightsParted_RC PARTITION (origin)
SELECT year, month, dayofmonth, dayofweek,
  deptime, crsdeptime, arrtime, crsarrTime,
  uniquecarrier, flightNum, tailnum,
  actualelapsedtime, crselapsedtime, airtime,
  arrdelay, depdelay, dest, distance,
  taxiIn, taxiout, cancelled, cancellationcode,
  diverted, carrierdelay, weatherdelay, nasdelay,
  securitydelay, lateaircraftdelay, origin
FROM flights;

SET hive.enforce.bucketing=true;
CREATE TABLE airportsB_RC (
  city String,
  iata String,
  airport String,
  state String,
  country String,
  lat String,
  long String)
CLUSTERED BY (city) INTO 32 BUCKETS
STORED AS RCFILE
TBLPROPERTIES ("orc.compress"="SNAPPY");

INSERT OVERWRITE TABLE airportsB_RC
SELECT
  city, iata, airport,
  state, country, lat, long
FROM airports;

4.
    queries from 2, but
      flights -> flightsParted_RC
      airports -> airportsB_RC

6. indexes
    CREATE INDEX flights_index
    ON TABLE flightsParted_RC (month) AS 'COMPACT'
    WITH DEFERRED REBUILD
    STORED AS RCFILE;

    # SHOW INDEX ON flightsP_RC;
    # DROP INDEX IF EXISTS flights_index ON flightsP_RC;

    CREATE INDEX airports_index
    ON TABLE airportsB_RC (city) AS 'COMPACT'
    WITH DEFERRED REBUILD
    STORED AS RCFILE;

    # SHOW INDEX ON airportsB_RC;
    # DROP INDEX IF EXISTS airports_index ON airportsB_RC;

    queries from 2

8. tez
    SET hive.execution.engine=tez;
    queries from 2, but
      flights -> flightsP_RC
      airports -> airportsB_RC

10. vectorization without tez
    SET hive.execution.engine=mr;
    SET hive.vectorized.execution.enabled = true;

    queries from 2, but
      flights -> flightsP_RC
      airports -> airportsB_RC

-- optimizations
SET mapred.compress.map.output=true;
SET mapred.output.compress=true;
SET hive.exec.compress.output=true;
SET hive.exec.parallel=true;
SET hive.cbo.enable=true;
SET hive.compute.query.using.stats=true;
SET hive.stats.fetch.column.stats=true;
SET hive.stats.fetch.partition.stats=true;

hive -hiveconf hive.root.logger=DEBUG,console
