package com.greencatsoft.angularjs.compile

import scala.scalajs.js

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.Injectable
import com.greencatsoft.angularjs.scope.Scope

trait Compile extends Injectable {

  type LinkFunction = js.Function2[Scope, CloneAttachFunction, Element]

  type CloneAttachFunction = js.Function2[Element, Scope, Unit]

  def apply(element: String, transclude: CloneAttachFunction, maxPriority: js.Number): LinkFunction = ???

  def apply(element: Element, transclude: CloneAttachFunction, maxPriority: js.Number): LinkFunction = ???
}

object Compile {

  val Name = "$compile"
}
