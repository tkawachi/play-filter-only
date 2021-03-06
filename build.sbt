import scalariform.formatter.preferences._

lazy val root = project.in(file("."))
  .settings(
    Seq(
      name := "play-filter-only",
      organization := "com.github.tkawachi",
      description := "Utility to apply Play's EssentialFilter to only specific paths",
      licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
      scmInfo := Some(ScmInfo(
        url("https://github.com/tkawachi/play-filter-only/"),
        "scm:git:github.com:tkawachi/play-filter-only.git"
      )),
      scalaVersion := "2.13.1",
      crossScalaVersions := Seq("2.12.8", "2.13.1"),
      scalacOptions := Seq("-deprecation", "-feature", "-unchecked", "-Xlint"),
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play" % "2.8.7" % "provided",
        "com.typesafe.play" %% "play-guice" % "2.8.7" % Test,
        "com.typesafe.play" %% "play-test" % "2.8.7" % Test,
        "org.scalatest" %% "scalatest" % "3.2.5" % Test
      ),
      wartremoverErrors ++= Warts.unsafe,
      scalariformPreferences := scalariformPreferences.value
        .setPreference(AlignSingleLineCaseStatements, true)
        .setPreference(DoubleIndentConstructorArguments, true)
        .setPreference(DanglingCloseParenthesis, Preserve)
    )
  )
