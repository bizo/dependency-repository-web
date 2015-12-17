import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._
import com.typesafe.sbteclipse.plugin.EclipsePlugin._


object TemplateAppBuild extends Build {
  val Organization = "com.bizo"
  val Name = "dependency-repository-web"
  val Version = "0.0.4"
  val ScalaVersion = "2.10.3"
  val ScalatraVersion = "2.4.0.RC3"

  lazy val project = Project (
    "dependency-repository-web",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "com.bizo" %% "dependency-repository-indexer" % "0.0.3",
        "org.apache.commons" % "commons-lang3" % "3.3.2",
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "com.google.guava" % "guava" % "18.0",
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "com.google.code.findbugs" % "jsr305" % "1.3.9" % "compile",  
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      ),
      EclipseKeys.withSource := true,
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
