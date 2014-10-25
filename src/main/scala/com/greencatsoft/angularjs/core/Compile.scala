package com.greencatsoft.angularjs.core

import scala.scalajs.js

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.injectable

@injectable("$compile")
trait Compile extends js.Object {

  type LinkFunction = js.Function2[Scope, CloneAttachFunction, Element]

  type CloneAttachFunction = js.Function2[Element, Scope, Unit]

  def apply(element: String, transclude: CloneAttachFunction, maxPriority: js.Number): LinkFunction = ???

  def apply(element: Element, transclude: CloneAttachFunction, maxPriority: js.Number): LinkFunction = ???
}
