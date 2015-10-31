package com.greencatsoft.angularjs.core

import scala.scalajs.js

import org.scalajs.dom.Element

import com.greencatsoft.angularjs.injectable

@js.native
@injectable("$compile")
trait Compile extends js.Object {

  type LinkFunction = js.Function2[Scope, CloneAttachFunction, Element]

  type CloneAttachFunction = js.Function2[Element, Scope, Unit]

  def apply(element: String): LinkFunction = js.native

  def apply(element: Element): LinkFunction = js.native

  def apply(element: String, transclude: CloneAttachFunction): LinkFunction = js.native

  def apply(element: Element, transclude: CloneAttachFunction): LinkFunction = js.native

  def apply(element: String, transclude: CloneAttachFunction, maxPriority: Number): LinkFunction = js.native

  def apply(element: Element, transclude: CloneAttachFunction, maxPriority: Number): LinkFunction = js.native
}
