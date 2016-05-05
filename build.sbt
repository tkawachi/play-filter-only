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
      scalaVersion := "2.11.7",
      crossScalaVersions := Seq("2.11.7", "2.10.5"),
      scalacOptions := Seq("-deprecation", "-feature", "-unchecked", "-Xlint"),
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play" % "2.4.3" % "provided"
      ),
      wartremoverErrors ++= Warts.unsafe
    ) ++ scalariformSettings: _*
  )
