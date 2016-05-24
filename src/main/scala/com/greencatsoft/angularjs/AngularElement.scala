package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.Scope
import org.scalajs.jquery.JQuery

import scala.scalajs.js
import scala.scalajs.js.UndefOr

@js.native
trait AngularElement extends JQuery {
  def controller[A <: Controller[_ <: Scope]](): UndefOr[ServiceWrapper[A]] = js.native
  def controller[A <: Controller[_ <: Scope]](name: String): UndefOr[ServiceWrapper[A]] = js.native
}
