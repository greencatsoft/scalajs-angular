package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.Injector
import com.greencatsoft.angularjs.internal.GlobalDefinitions.angular

import org.scalajs.dom.Element

import scala.language.experimental.macros
import scala.language.implicitConversions

import scala.scalajs.js
import scala.scalajs.js.JSConverters.genTravConvertible2JSRichGenTrav
import scala.scalajs.js.|

object Angular {

  def apply(name: String): Option[Module] =
    angular.module(name).toOption.map(new Module(_))

  def bootstrap(element: Element, modules: String*): Injector = angular.bootstrap(element, modules.toJSArray)

  def injector: Injector = angular.injector()

  def injector(modules: String*): Injector = angular.injector(modules.toJSArray)

  def module(name: String, dependencies: Seq[String] = Nil): Module =
    new Module(angular.module(name, dependencies.toJSArray))

  def extend(dst: js.Object, src: js.Object*): js.Object = angular.extend(dst, src)

  def merge(dst: js.Object, src: js.Object*): js.Object = angular.extend(dst, src)

  def toJson(obj: Any): String = angular.toJson(obj)

  def toJson(obj: Any, pretty: Boolean | Integer): String = angular.toJson(obj, pretty)

  def fromJson(json: String): Any = angular.fromJson(json)

  implicit def element(elem: Element): AngularElement = angular.element(elem)

  /** Retrieve the angular name of an [[com.greencatsoft.angularjs.injectable]] component.
    *
    * For example when implementing a directive with a controller we can now write {{{
    *   override val controller = Some(Angular.nameOf[MyController])
    * }}}
    *
    * @tparam A type annotated with `@injectable` to retrieve the angular name for.
    * @return the angular name of the specified type.
    */
  def nameOf[A]: String = macro internal.Angular.nameOf[A]
}
