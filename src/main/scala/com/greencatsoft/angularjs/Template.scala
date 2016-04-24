package com.greencatsoft.angularjs

import scala.language.experimental.macros

trait Templated {

  val templateUrl: String
}

trait TemplateSource {

  val template: String
}

/** Helper to embed templates from HTML source files as `String`.
  *
  * For example load a template from a file relative to the current source file: {{{
  *   val templateUrl = Template.relativeTemplate("./my-template.html")
  * }}}
  *
  * If the following code is placed in `MyDirective.scala` the content of `MyDirective.html` from the same source
  * directory will be embedded: {{{
  *   val templateUrl = Template.companionTemplate
  * }}}
  */
object Template {
  /** Provides the content from the specified source file as `String` constant.
    *
    * Fails at compile time, if the specified file does not exist.
    *
    * @param relativePath path relative to the source file, this method is called from.
    * @return the content of the specified file.
    */
  def relativeTemplate(relativePath: String): String = macro internal.Template.relativeTemplate

  /** Provides the content from the companion template as `String` constant.
    * The companion template is a file which is named exactly as the Scala source file, this method is called from,
    * but with a `.html` extension instead of the `.scala` extension.
    *
    * Fails at compile time, if the specified file does not exist.
    *
    * @return the content of the companion template file.
    */
  def companionTemplate: String = macro internal.Template.companionTemplate
}