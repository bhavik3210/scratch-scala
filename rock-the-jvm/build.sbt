name := "rock-the-jvm"
organization in ThisBuild := "com.rtj"
scalaVersion := "2.13.3"

lazy val global = project 
	.in(file("."))
	.aggregate(
		scalaAtLightSpeed,
		scalaBeginners
	)

lazy val scalaAtLightSpeed = project
	.settings(
		name := "scala-at-light-speed"
	)

lazy val scalaBeginners = project
	.settings(
		name := "scala-beginners"
	)