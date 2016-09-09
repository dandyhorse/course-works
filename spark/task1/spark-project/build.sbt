
name := "spark-project"
version := "0.0.1"

scalaVersion := "2.10.6"

publishMavenStyle := true

resolvers ++= Seq("Hortonworks Repository" at "http://repo.hortonworks.com/content/repositories/releases/",
  "Hortonworks Jetty Maven Repository" at "http://repo.hortonworks.com/content/repositories/jetty-hadoop/")

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % "2.7.1" % Provided
    exclude(org = "javax.servlet", name = "servlet-api")
    exclude(org = "javax.servlet", name = "jsp-api"),
  "org.apache.hadoop" % "hadoop-core" % "1.2.1" % Provided
    exclude(org = "javax.servlet", name = "servlet-api")
    exclude(org = "javax.servlet", name = "jsp-api"),
  "org.apache.spark" % "spark-core_2.10" % "1.6.2" % Provided
    excludeAll ExclusionRule(organization = "org.apache.hadoop"),
  "eu.bitwalker" % "UserAgentUtils" % "1.20",
  "com.holdenkarau" % "spark-testing-base_2.10" % "1.6.1_0.4.4" % Test
    excludeAll ExclusionRule(organization = "org.apache.hadoop")
).map(
  _.excludeAll(ExclusionRule(organization = "org.mortbay.jetty"))
)

autoScalaLibrary := false

mainClass in assembly := Some("com.epam.training.spark.hw1.Main")
assemblyJarName in assembly := "spark-hw1.jar"
