val zioVersion = "2.0.0"
val zioHttpVersion = "2.0.0-RC10"
val zioJsonVersion = "0.3.0-RC10"
val logbackVersion = "1.2.11"
val testcontainersVersion      = "1.17.3"
val testcontainersScalaVersion = "0.40.8"
val quillVersion = "4.0.0"
val postgresqlVersion = "42.4.0"
val zioConfigVersion = "3.0.1"
val zioMockVersion = "1.0.0-RC8"

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        name := "zio-quickstart",
        organization := "com.example",
        version := "0.0.1",
        scalaVersion := "3.1.3",
      )
    ),
    name := "zio-quickstart",
    libraryDependencies ++= Seq(
      "io.getquill" %% "quill-jdbc" % quillVersion excludeAll (
        ExclusionRule(organization = "org.scala-lang.modules")
      ),
      "io.getquill" %% "quill-jdbc-zio" % quillVersion excludeAll (
        ExclusionRule(organization = "org.scala-lang.modules")
      ),
      "io.getquill" %% "quill-jasync-postgres" % quillVersion excludeAll (
        ExclusionRule(organization = "org.scala-lang.modules")
      ),
      "org.postgresql" % "postgresql" % postgresqlVersion,
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,
      "io.d11" %% "zhttp" % zioHttpVersion,
      "dev.zio" %% "zio-config" % zioConfigVersion,
      "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,
      "ch.qos.logback" % "logback-classic" % logbackVersion,
      "dev.zio" %% "zio-json" % zioJsonVersion,
      "dev.zio" %% "zio-test" % zioVersion % Test,
      "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
      "dev.zio" %% "zio-test-junit" % zioVersion % Test,
      "dev.zio" %% "zio-mock" % zioMockVersion % Test,
      "com.dimafeng"      %% "testcontainers-scala-postgresql" % testcontainersScalaVersion % Test,
      "org.testcontainers" % "testcontainers"                  % testcontainersVersion      % Test,
      "org.testcontainers" % "database-commons"                % testcontainersVersion      % Test,
      "org.testcontainers" % "postgresql"                      % testcontainersVersion      % Test,
      "org.testcontainers" % "jdbc"                            % testcontainersVersion      % Test,
      "dev.zio" %% "zio-test-magnolia" % zioVersion % Test,
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
  )
  .enablePlugins(JavaAppPackaging)
