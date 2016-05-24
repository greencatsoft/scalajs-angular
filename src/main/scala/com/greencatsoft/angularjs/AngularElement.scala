package com.greencatsoft.angularjs

import com.greencatsoft.angularjs.core.Scope
import org.scalajs.dom
import org.scalajs.jquery.JQuery

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.UndefOr

@js.native
@injectable("$element")
trait AngularElement extends JQuery {
  def controller[A <: Controller[_ <: Scope]](): UndefOr[ServiceWrapper[A]] = js.native
  def controller[A <: Controller[_ <: Scope]](name: String): UndefOr[ServiceWrapper[A]] = js.native
}

object AngularElement {

  def apply(e: dom.Element): AngularElement = Angular.element(e)

  def apply(jQuery: JQuery): AngularElement = jQuery.asInstanceOf[AngularElement]

  implicit def jQueryToAngular(jQuery: JQuery): AngularElement = apply(jQuery)

  implicit class IterableAngularElement(val e: AngularElement) extends AnyVal {

    def foreach(f: (Int, AngularElement) => Unit): Unit = {
      for (i <- 0 until e.length)
        f(i, AngularElement(e.get(i)))
    }

    def foreach(f: AngularElement => Unit): Unit =
      foreach((_, e) => f(e))
  }
}
