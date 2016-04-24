package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.Scope
import org.scalajs.jquery.JQuery

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.UndefOr

@js.native
trait AngularElement extends JQuery {
  def controller[A <: Controller[_ <: Scope]](): UndefOr[ControllerWrapper[A]] = js.native
  def controller[A <: Controller[_ <: Scope]](name: String): UndefOr[ControllerWrapper[A]] = js.native
}

@js.native
trait ControllerWrapper[A <: Controller[_ <: Scope]] extends js.Object {
  var controller: A = js.native
}

object ControllerWrapper {

  implicit def wrapperToController[A <: Controller[_ <: Scope]](wrapper: ControllerWrapper[A]): A =
    wrapper.controller
}