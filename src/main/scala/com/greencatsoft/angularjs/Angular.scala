package com.greencatsoft.angularjs

import scala.language.implicitConversions
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav

import org.scalajs.dom.Element

object Angular {

  import internal.angular

  def apply(name: String): Option[Module] =
    angular.module(name).toOption.map(new Module(_))

  def module(name: String, dependencies: Seq[String] = Nil): Module =
    new Module(angular.module(name, dependencies.toJSArray))

  implicit def element(elem: Element): AngularElement = angular.element(elem)
}
