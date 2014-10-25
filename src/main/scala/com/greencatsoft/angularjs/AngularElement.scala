package com.greencatsoft.angularjs

import scala.language.implicitConversions
import scala.scalajs.js

import org.scalajs.dom.{ Element, Event }

trait AngularElement extends Element {

  def bind[T <: Event](event: String, handler: js.Function1[T, _]): Unit = ???

  def unbind[T <: Event](event: String, handler: js.Function1[T, _]): Unit = ???
}