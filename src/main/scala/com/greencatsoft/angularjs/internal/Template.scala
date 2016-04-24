package com.greencatsoft.angularjs.internal

import java.io.File

import scala.io.Source
import scala.reflect.macros.blackbox.Context

/** Macro implementation for [[com.greencatsoft.angularjs.Template]]. */
private[angularjs] object Template {
  def relativeTemplate(c: Context)(relativePath: c.Expr[String]): c.Expr[String] = {
    import c.universe._

    val Literal(Constant(relativePathConstant: String)) = relativePath.tree
    val relativeTemplateFile = new File(sourceFile(c), relativePathConstant)
    val content = fileAsString(c)(relativeTemplateFile)
    c.Expr[String](q"$content")
  }

  def companionTemplate(c: Context): c.Expr[String] = {
    import c.universe._

    val companionTemplateFile = {
      val sourcePath = sourceFile(c).getAbsolutePath
      val templatePath = if (sourcePath.endsWith(".scala"))
        sourcePath.dropRight(".scala".length) + ".html"
      else
        c.abort(c.enclosingPosition, s"Companion templates are only supported for scala files. Current contest is $sourcePath.")
      new File(templatePath)
    }
    val content = fileAsString(c)(companionTemplateFile)
    c.Expr[String](q"$content")
  }

  private def sourceFile(c: Context): File =
    c.enclosingPosition.source.file.file

  private def fileAsString(c: Context)(file: File): String = {
    if (!file.exists)
      c.abort(c.enclosingPosition, s"No template found at ${file.getAbsolutePath}")

    val source = Source.fromFile(file)
    val content = source.mkString
    source.close()
    content
  }
}