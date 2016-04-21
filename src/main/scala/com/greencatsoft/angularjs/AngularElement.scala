package com.greencatsoft.angularjs

import org.scalajs.dom.Event
import org.scalajs.dom.html.Element

import scala.language.implicitConversions
import scala.scalajs.js

@js.native
trait AngularElement extends Element {

  def bind[T <: Event](event: String, handler: js.Function1[T, _]): Unit = js.native

  def unbind[T <: Event](event: String, handler: js.Function1[T, _]): Unit = js.native
}
