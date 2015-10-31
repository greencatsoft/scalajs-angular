package com.greencatsoft.angularjs

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.internal.GlobalDefinitions.angular

object Angular {

  def apply(name: String): Option[Module] =
    angular.module(name).toOption.map(new Module(_))

  def module(name: String, dependencies: Seq[String] = Nil): Module =
    new Module(angular.module(name, dependencies.toJSArray))

  def extend(dst: js.Object, src: js.Object*): js.Object = angular.extend(dst, src)

  def merge(dst: js.Object, src: js.Object*): js.Object = angular.extend(dst, src)

  implicit def element(elem: Element): AngularElement = angular.element(elem)
}
