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
      scalaVersion := "2.13.12",
      crossScalaVersions := Seq("2.13.12", "3.3.1"),
      scalacOptions := Seq("-deprecation", "-feature", "-unchecked", "-Xlint"),
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play" % "2.9.1" % "provided",
        "com.typesafe.play" %% "play-guice" % "2.9.1" % Test,
        "com.typesafe.play" %% "play-test" % "2.9.1" % Test,
        "org.scalatest" %% "scalatest" % "3.2.18" % Test
      ),
      wartremoverErrors ++= Warts.unsafe,
      scalariformPreferences := scalariformPreferences.value
        .setPreference(AlignSingleLineCaseStatements, true)
        .setPreference(DoubleIndentConstructorArguments, true)
        .setPreference(DanglingCloseParenthesis, Preserve)
    )
  )
