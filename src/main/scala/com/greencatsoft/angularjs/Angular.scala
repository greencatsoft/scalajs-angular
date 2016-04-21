package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.Injector
import com.greencatsoft.angularjs.internal.GlobalDefinitions.angular
import com.greencatsoft.angularjs.internal.ServiceProxy
import org.scalajs.dom.html.Element

import scala.language.experimental.macros
import scala.language.implicitConversions
import scala.reflect.macros.blackbox.Context
import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.|

object Angular {

  def apply(name: String): Option[Module] =
    angular.module(name).toOption.map(new Module(_))

  def injector(modules: String*): Injector = angular.injector(modules.toJSArray)

  def module(name: String, dependencies: Seq[String] = Nil): Module =
    new Module(angular.module(name, dependencies.toJSArray))

  def extend(dst: js.Object, src: js.Object*): js.Object = angular.extend(dst, src)

  def merge(dst: js.Object, src: js.Object*): js.Object = angular.extend(dst, src)

  def toJson(obj: Any): String = angular.toJson(obj)

  def toJson(obj: Any, pretty: Boolean | Integer): String = angular.toJson(obj, pretty)

  def fromJson(json: String): Any = angular.fromJson(json)

  implicit def element(elem: Element): AngularElement = angular.element(elem)
}
