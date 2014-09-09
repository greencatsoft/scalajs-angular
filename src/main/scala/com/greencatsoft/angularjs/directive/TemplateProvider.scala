package com.greencatsoft.angularjs.directive

import scala.scalajs.js
import scala.scalajs.js.Any.fromFunction2

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.ConfigBuilder

trait TemplateProvider extends ConfigBuilder {
  this: Directive =>

  val template: String

  def getTemplate(elems: Seq[Element], attrs: Attributes): String = template

  abstract override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("template") = (elems: js.Array[Element], attrs: Attributes) => getTemplate(elems, attrs)

    super.buildConfig(config)
  }
}

trait TemplateUrlProvider extends ConfigBuilder {
  this: Directive =>

  val templateUrl: String

  def getTemplateUrl(elems: Seq[Element], attrs: Attributes): String = templateUrl

  abstract override def buildConfig(config: js.Dictionary[js.Any]): js.Dictionary[js.Any] = {
    config("templateUrl") = (elems: js.Array[Element], attrs: Attributes) => getTemplateUrl(elems, attrs)

    super.buildConfig(config)
  }
}
