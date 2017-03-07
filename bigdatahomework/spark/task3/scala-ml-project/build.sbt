name := "scala-ml-project"

version := "1.0"

scalaVersion := "2.10.6"

val sparkVersion = "1.6.0"

resolvers ++= Seq("Hortonworks Repository" at "http://repo.hortonworks.com/content/repositories/releases/",
  "Hortonworks Jetty Maven Repository" at "http://repo.hortonworks.com/content/repositories/jetty-hadoop/")

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % "2.7.1" % Provided
    exclude(org = "javax.servlet", name = "servlet-api")
    exclude(org = "javax.servlet", name = "jsp-api"),
  "org.apache.hadoop" % "hadoop-core" % "1.2.1" % Provided
    exclude(org = "javax.servlet", name = "servlet-api")
    exclude(org = "javax.servlet", name = "jsp-api"),
  "org.apache.spark" % "spark-core_2.10" % sparkVersion % Provided
    excludeAll ExclusionRule(organization = "org.apache.hadoop"),
  "org.apache.spark" % "spark-mllib_2.10" % sparkVersion % Provided
).map(
  _.excludeAll(ExclusionRule(organization = "org.mortbay.jetty"))
)

autoScalaLibrary := false

mainClass in assembly := Some("com.epam.training.ml.hw1.Main")
assemblyJarName in assembly := "ml-hw3.jar"
